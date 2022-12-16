package ddsutn.services.georef;

import ddsutn.domain.location.District;
import ddsutn.services.DistrictSvc;
import ddsutn.services.MunicipalitySvc;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class DistrictResponse {

  public int id;
  public String nombre;
  public int codPostal;
  public MunicipalityResponse municipio;

  public District toDistrict(DistrictSvc districtSvc, MunicipalitySvc municipalitySvc) {

    District existingDistrict = districtSvc.findById(id);

    if (existingDistrict != null) {
      return existingDistrict;
    } else {
      return new District(id,
          Arrays.stream(nombre.split(" "))
              .map(word -> {
                if (word.length() > 1) {
                  return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
                } else return word;
              })              .collect(Collectors.joining(" ")),
          codPostal,
          municipalitySvc.findById(municipio.id));
    }
  }

}
