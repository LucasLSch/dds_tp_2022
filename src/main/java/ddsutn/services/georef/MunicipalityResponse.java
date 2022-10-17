package ddsutn.services.georef;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MunicipalityResponse {

  public int id;
  public String name;
  public ProvinceResponse province;

}
