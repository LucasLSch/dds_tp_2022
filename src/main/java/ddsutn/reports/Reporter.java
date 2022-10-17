package ddsutn.reports;

import ddsutn.repositories.TerritorialSectorRepo;
import ddsutn.domain.measurements.CarbonFootprint;
import ddsutn.domain.measurements.unit.Unit;
import ddsutn.domain.organization.OrgType;
import ddsutn.domain.organization.Organization;
import ddsutn.domain.territories.TerritorialSector;
import org.json.JSONArray;
import org.json.JSONObject;
import ddsutn.repositories.OrganizationRepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Reporter {

    public String generateTerritorialSectorCFReport(Set<Unit> units) {
        JSONArray report = new JSONArray();
        List<TerritorialSector> territorialSectors = TerritorialSectorRepo.getInstance().getAll();
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
        List<Organization> organizations = OrganizationRepo.getInstance().getAll();
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

    //TODO arreglar el get
//    public String generateTerritorialSectorHistoricalCFReport(Set<Unit> units, Long territorialSectorId) {
//        JSONObject report = new JSONObject();
//        report.put("territorial_sector_id", territorialSectorId);
//        JSONArray jsonArrayCF = new JSONArray();
//        TerritorialSector ts = TerritorialSectorRepo.getInstance().getById(territorialSectorId);
//        CarbonFootprintRepo.getInstance().getAll()
//                .stream()
//                .filter( cf -> cf.getTerritorialSector().getId().equals(territorialSectorId) )
//                .sorted(Comparator.comparing(CarbonFootprint::getDate))
//                .forEach(cf -> jsonArrayCF.put(new JSONObject()
//                        .put("id", cf.getId())
//                        .put("value", cf.getValue())
//                        .put("date", cf.getDate())));
//        report.put("carbon_footprints", jsonArrayCF);
//        return report.toString();
//    }

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
