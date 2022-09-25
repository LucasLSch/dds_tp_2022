package reports;

import domain.measurements.CarbonFootprint;
import domain.measurements.unit.Unit;
import domain.organization.OrgType;
import domain.organization.Organization;
import domain.territories.TerritorialSector;
import org.json.JSONArray;
import org.json.JSONObject;
import repositories.CarbonFootprintRepo;
import repositories.OrganizationRepo;
import repositories.TerritorialSectorRepo;

import java.util.*;
import java.util.stream.Stream;

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

    public String generateTerritorialSectorHistoricalCFReport(Set<Unit> units, Long territorialSectorId) {
        JSONObject report = new JSONObject();
        report.put("territorial_sector_id", territorialSectorId);
        JSONArray jsonArrayCF = new JSONArray();
        TerritorialSector ts = TerritorialSectorRepo.getInstance().getById(territorialSectorId);
        CarbonFootprintRepo.getInstance().getAll()
                .stream()
                .filter( cf -> cf.getTerritorialSector().getId().equals(territorialSectorId) )
                .sorted(Comparator.comparing(CarbonFootprint::getDate))
                .forEach(cf -> jsonArrayCF.put(new JSONObject()
                        .put("id", cf.getId())
                        .put("value", cf.getValue())
                        .put("date", cf.getDate())));
        report.put("carbon_footprints", jsonArrayCF);
        return report.toString();
    }

    public String generateOrganizationHistoricalCFReport(Set<Unit> units, Long organizationSectorId) {
        JSONObject report = new JSONObject();
        report.put("organization_id", organizationSectorId);
        JSONArray jsonArrayCF = new JSONArray();
        Organization ts = OrganizationRepo.getInstance().getById(organizationSectorId);
        CarbonFootprintRepo.getInstance().getAll()
                .stream()
                .filter( cf -> cf.getOrganization().getId().equals(organizationSectorId) )
                .sorted(Comparator.comparing(CarbonFootprint::getDate))
                .forEach(cf -> jsonArrayCF.put(new JSONObject()
                        .put("id", cf.getId())
                        .put("value", cf.getValue())
                        .put("date", cf.getDate())));
        report.put("carbon_footprints", jsonArrayCF);
        return report.toString();
    }
}
