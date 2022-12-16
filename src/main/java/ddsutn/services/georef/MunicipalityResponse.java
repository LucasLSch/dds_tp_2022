package ddsutn.services.georef;

import ddsutn.domain.location.Municipality;
import ddsutn.services.MunicipalitySvc;
import ddsutn.services.ProvinceSvc;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class MunicipalityResponse {

  public int id;
  public String nombre;
  public ProvinceResponse provincia;

  public Municipality toMunicipality(MunicipalitySvc municipalitySvc, ProvinceSvc provinceSvc) {

    Municipality existingMunicipality = municipalitySvc.findById(id);

    if (existingMunicipality != null) {
      return existingMunicipality;
    } else {
      return new Municipality(id,
          Arrays.stream(nombre.split(" "))
              .map(word -> {
                if (word.length() > 1) {
                  return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
                } else return word;
              })              .collect(Collectors.joining(" ")),
          provinceSvc.findById(provincia.id));
    }
  }

}
