package services.georef;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LocalityResponse {

  public int id;
  public String name;
  public int postalCode;
  public MunicipalityResponse municipality;

}
