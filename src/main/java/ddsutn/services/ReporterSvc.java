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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class ReporterSvc {

  @Autowired
  private TerritorialSectorRepo territorialSectorRepo;

  @Autowired
  private OrganizationRepo organizationRepo;

  @Autowired
  private CarbonFootprintRepo carbonFootprintRepo;

  public String generateTerritorialSectorCFReport(Set<Unit> units) {
    JSONArray report = new JSONArray();
    List<TerritorialSector> territorialSectors = (List<TerritorialSector>) territorialSectorRepo.findAll();
    territorialSectors.forEach(ts -> {
      CarbonFootprint cf = ts.getCF(units);
      report.put(new JSONObject()
          .put("id", ts.getId())
          .put("CarbonFootprint", cf.getValue()));
    });
    return new JSONObject().toString();
  }

  public String generateOrganizationTypeCFReport(Set<Unit> units) {
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
          .put("OrgType", ot.name())
          .put("CarbonFootprint", value));
    });
    return report.toString();
  }

  //TODO arreglar los de abajo

//    public String generateTerritorialSectorHistoricalCFReport(Set<Unit> units, Long territorialSectorId) {
//        JSONObject report = new JSONObject();
//        report.put("territorial_sector_id", territorialSectorId);
//        JSONArray jsonArrayCF = new JSONArray();
//        TerritorialSector ts = territorialSectorRepo.findById(territorialSectorId).orElse(null);
//        if(ts != null) {
//          StreamSupport.stream(carbonFootprintRepo.findAll().spliterator(), false)
//              .filter( cf -> cf.getTerritorialSector().getId().equals(territorialSectorId) )
//              .sorted(Comparator.comparing(CarbonFootprint::getDate))
//              .forEach(cf -> jsonArrayCF.put(new JSONObject()
//                  .put("id", cf.getId())
//                  .put("value", cf.getValue())
//                  .put("date", cf.getDate())));
//          report.put("carbon_footprints", jsonArrayCF);
//
//        }
//
//        return report.toString();
//    }
//
//    public String generateOrganizationHistoricalCFReport(Set<Unit> units, Long organizationSectorId) {
//        JSONObject report = new JSONObject();
//        report.put("organization_id", organizationSectorId);
//        JSONArray jsonArrayCF = new JSONArray();
//        Organization ts = OrganizationRepo.getInstance().getById(organizationSectorId);
//        CarbonFootprintRepo.getInstance().getAll()
//                .stream()
//                .filter( cf -> cf.getOrganization().getId().equals(organizationSectorId) )
//                .sorted(Comparator.comparing(CarbonFootprint::getDate))
//                .forEach(cf -> jsonArrayCF.put(new JSONObject()
//                        .put("id", cf.getId())
//                        .put("value", cf.getValue())
//                        .put("date", cf.getDate())));
//        report.put("carbon_footprints", jsonArrayCF);
//        return report.toString();
//    }

}
