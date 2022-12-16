package ddsutn.services.georef;

import ddsutn.domain.location.Country;
import ddsutn.services.CountrySvc;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class CountryResponse {

  public int id;
  public String nombre;

  public Country toCountry(CountrySvc countrySvc) {
    Country existingCountry = countrySvc.findById(id);

    if (existingCountry != null) {
      return existingCountry;
    } else {
      return new Country(id,
          Arrays.stream(nombre.split(" "))
              .map(word -> {
                if (word.length() > 1) {
                  return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
                } else return word;
              })
              .collect(Collectors.joining(" ")));
    }
  }
}
