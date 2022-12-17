package ddsutn.services;

import ddsutn.domain.measurements.CarbonFootprint;
import ddsutn.domain.measurements.unit.Unit;
import ddsutn.domain.measurements.unit.UnitExpression;
import ddsutn.domain.organization.Member;
import ddsutn.domain.organization.OrgType;
import ddsutn.domain.organization.Organization;
import ddsutn.domain.territories.TerritorialSector;
import ddsutn.repositories.CarbonFootprintRepo;
import ddsutn.repositories.MemberRepo;
import ddsutn.repositories.OrganizationRepo;
import ddsutn.repositories.TerritorialSectorRepo;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReporterSvc {

  @Autowired
  private TerritorialSectorRepo territorialSectorRepo;

  @Autowired
  private OrganizationRepo organizationRepo;

  @Autowired
  private MemberRepo memberRepo;

  @Autowired
  private CarbonFootprintRepo carbonFootprintRepo;


  // --------------- Reportes Pedidos Por Administradores --------------- //

  // Todos los sectores territoriales con su última huella
  public JSONArray territorialSectorsCfReport(Set<Unit> units) {
    JSONArray report = new JSONArray();
    List<TerritorialSector> territorialSectors =
        (List<TerritorialSector>) territorialSectorRepo.findAll();
    territorialSectors.forEach(ts -> {
      CarbonFootprint cf = ts.getCarbonFootprint(units);
      report.put(new JSONObject()
          .put("territorialSectorId", ts.getId())
          .put("carbonFootprint (" + UnitExpression.printUnits(units) + ")", cf.getValue()));
    });
    return report;
  }

  // Todas las organizaciones con su última huella, ordenadas según tipo de organización
  public JSONArray organizationTypeCfReport(Set<Unit> units) {
    JSONArray report = new JSONArray();
    List<OrgType> orgTypes = new ArrayList<>();
    Collections.addAll(orgTypes, OrgType.values());
    List<Organization> organizations = (List<Organization>) organizationRepo.findAll();
    orgTypes.forEach(ot -> {
      Double value = organizations
          .stream()
          .filter(org -> org.getType().equals(ot))
          .mapToDouble(org -> org.getTotalCarbonFootprint(units).getValue())
          .sum();
      report.put(new JSONObject()
          .put("orgType", ot.name())
          .put("carbonFootprint (" + UnitExpression.printUnits(units) + ")", value));
    });
    return report;
  }

  // --------------- Reportes Pedidos Por Agentes Territoriales --------------- //

  // Todas las huellas de carbono ordenadas por fecha de un sector territorial
  public JSONObject territorialSectorHistoricalCFReport(Set<Unit> units,
                                                        Long territorialSectorId) {
    JSONObject report = new JSONObject();
    report.put("territorialSectorId", territorialSectorId);
    TerritorialSector ts = territorialSectorRepo.findById(territorialSectorId).orElse(null);

    if (ts != null) {
      List<CarbonFootprint> cfs = this.carbonFootprintRepo.findByTerritorialSector(territorialSectorId);
      report.put("carbonFootprints (" + UnitExpression.printUnits(units) + ")", this.historicalSortedReport(cfs, units));
    }

    return report;
  }

  // Todas las huellas de carbono de un sector territorial, por organización
  public JSONObject territorialSectorOrganizationCFReport(Set<Unit> units,
                                                          Long territorialSectorId) {
    JSONObject report = new JSONObject();
    report.put("territorialSectorId", territorialSectorId);
    JSONArray organizationsReport = new JSONArray();
    TerritorialSector ts = territorialSectorRepo.findById(territorialSectorId).orElse(null);

    if (ts != null) {
      ts.getOrganizations().forEach(org -> {
        organizationsReport.put(new JSONObject()
            .put("id", org.getId())
            .put("socialObjective", org.getSocialObjective())
            .put("value", org.getTotalCarbonFootprint(units)));
      });

      report.put("organizationCarbonFootprints (" + UnitExpression.printUnits(units) + ")", organizationsReport);
    }

    return report;
  }


  // --------------- Reportes Pedidos Por Organizaciones --------------- //

  // Todas las huellas de carbono ordenadas por fecha de una organización
  public JSONObject organizationHistoricalCFReport(Set<Unit> units,
                                                   Long organizationId) {
    JSONObject report = new JSONObject();
    report.put("organizationId", organizationId);
    Organization org = organizationRepo.findById(organizationId).orElse(null);

    if (org != null) {
      List<CarbonFootprint> cfs = this.carbonFootprintRepo.findByOrganization(organizationId);
      report.put("carbonFootprints (" + UnitExpression.printUnits(units) + ")", this.historicalSortedReport(cfs, units));
    }

    return report;
  }

  // Todas las huellas de carbono de una organización, por sector
  public JSONObject organizationSectorCFReport(Set<Unit> units,
                                               Long organizationId) {
    JSONObject report = new JSONObject();
    report.put("organizationId", organizationId);
    JSONArray sectorsReports = new JSONArray();
    Organization org = organizationRepo.findById(organizationId).orElse(null);

    if (org != null) {
      org.getSectors().forEach(sector -> {
        sectorsReports.put(new JSONObject()
            .put("id", sector.getId())
            .put("name", sector.getName())
            .put("value", sector.getCarbonFootprint(units)));
      });

      report.put("sectorCarbonFootprints (" + UnitExpression.printUnits(units) + ")", sectorsReports);
    }

    return report;
  }


  // --------------- Reportes Pedidos Por Miembros --------------- //

  // Todas las huellas de carbono ordenadas por fecha de un miembro
  public JSONObject memberHistoricalCFReport(Set<Unit> units,
                                             Long memberId) {
    JSONObject report = new JSONObject();
    report.put("memberId", memberId);
    Member member = memberRepo.findById(memberId).orElse(null);

    if (member != null) {
      List<CarbonFootprint> cfs = this.carbonFootprintRepo.findByMember(memberId);
      report.put("carbonFootprints (" + UnitExpression.printUnits(units) + ")", this.historicalSortedReport(cfs, units));
    }

    return report;
  }

  // Todas las huellas de carbono de un miembro, por trayecto
  public JSONObject memberJourneyCFReport(Set<Unit> units,
                                          Long memberId) {

    JSONObject report = new JSONObject();
    report.put("memberId", memberId);
    JSONArray journeyReports = new JSONArray();
    Member member = memberRepo.findById(memberId).orElse(null);

    if (member != null) {
      member.getJourneys().forEach(journey -> {
        journeyReports.put(new JSONObject()
            .put("id", journey.getId())
            .put("startingLocation", journey.getStartingLocation().print())
            .put("endingLocation", journey.getEndingLocation().print())
            .put("value", journey.getCarbonFootprint(units).getValue()));
      });

      report.put("journeyCarbonFootprints (" + UnitExpression.printUnits(units) + ")", journeyReports);
    } else {
      return null;
    }

    CarbonFootprint pcf = member.getPersonalCF();

    carbonFootprintRepo.save(pcf);

    return report;
  }


  // --------------- Utils --------------- //

  public JSONArray historicalSortedReport(List<CarbonFootprint> carbonFootprintList, Set<Unit> units) {
    JSONArray result = new JSONArray();

    carbonFootprintList
        .stream()
        .map(cf -> cf.getOn(units))
        .sorted(Comparator.comparing(CarbonFootprint::getDate))
        .forEach(cf -> result.put(new JSONObject()
            .put("id", cf.getId())
            .put("value", cf.getValue())
            .put("date", cf.getDate()))
        );

    return result;
  }

}
