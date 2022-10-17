package ddsutn.services.georef;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProvinceResponse {

  public int id;
  public String name;
  public CountryResponse country;

}
