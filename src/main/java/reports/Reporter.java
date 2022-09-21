package reports;

import domain.measurements.CarbonFootprint;
import domain.measurements.unit.Unit;
import domain.organization.OrgType;
import domain.organization.Organization;
import domain.territories.TerritorialSector;
import org.json.JSONArray;
import org.json.JSONObject;
import repositories.OrganizationRepo;
import repositories.TerritorialSectorRepo;

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

    public String generateTerritorialSectorHistoricalCFReport(Set<Unit> units) {
      
    }
}
