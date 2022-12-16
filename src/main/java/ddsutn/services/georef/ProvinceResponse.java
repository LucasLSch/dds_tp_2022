package ddsutn.services.georef;

import ddsutn.domain.location.Province;
import ddsutn.services.CountrySvc;
import ddsutn.services.ProvinceSvc;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class ProvinceResponse {

  public int id;
  public String nombre;
  public CountryResponse pais;

  public Province toProvince(ProvinceSvc provinceSvc, CountrySvc countrySvc) {

    Province existingProvince = provinceSvc.findById(id);

    if (existingProvince != null) {
      return existingProvince;
    } else {
      return new Province(
          id,
          Arrays.stream(nombre.split(" "))
              .map(word -> {
                if (word.length() > 1) {
                  return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
                } else return word;
              })              .collect(Collectors.joining(" ")),
          countrySvc.findById(pais.id));
    }
  }

}
