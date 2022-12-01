package ddsutn.services;

import ddsutn.domain.measurements.CarbonFootprint;
import ddsutn.domain.measurements.unit.Unit;
import ddsutn.domain.organization.OrgType;
import ddsutn.domain.organization.Organization;
import ddsutn.domain.territories.TerritorialSector;
import ddsutn.repositories.CarbonFootprintRepo;
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
  private CarbonFootprintRepo carbonFootprintRepo;

  public JSONArray territorialSectorCfReport(Set<Unit> units) {
    JSONArray report = new JSONArray();
    List<TerritorialSector> territorialSectors =
        (List<TerritorialSector>) territorialSectorRepo.findAll();
    territorialSectors.forEach(ts -> {
      CarbonFootprint cf = ts.getCarbonFootprint(units);
      report.put(new JSONObject()
          .put("territorialSectorId", ts.getId())
          .put("carbonFootprint", cf.getValue()));
    });
    return report;
  }

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
          .put("carbonFootprint", value));
    });
    return report;
  }

  public JSONObject territorialSectorHistoricalCFReport(Set<Unit> units,
                                                        Long territorialSectorId) {
    JSONObject report = new JSONObject();
    report.put("territorialSectorId", territorialSectorId);
    JSONArray jsonArrayCF = new JSONArray();
    TerritorialSector ts = territorialSectorRepo.findById(territorialSectorId).orElse(null);

    if (ts != null) {
      List<CarbonFootprint> cfs = this.carbonFootprintRepo.findByTerritorialSector(territorialSectorId);
      report.put("carbonFootprints", this.historicalSortedReport(cfs, units));
    }

    return report;
  }

  public JSONObject organizationHistoricalCFReport(Set<Unit> units,
                                                   Long organizationId) {
    JSONObject report = new JSONObject();
    report.put("organizationId", organizationId);
    Organization org = organizationRepo.findById(organizationId).orElse(null);

    if (org != null) {
      List<CarbonFootprint> cfs = this.carbonFootprintRepo.findByOrganization(organizationId);
      report.put("carbonFootprints", this.historicalSortedReport(cfs, units));
    }

    return report;
  }


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
