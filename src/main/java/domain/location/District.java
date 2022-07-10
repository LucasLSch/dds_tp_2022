package domain.location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import services.georef.MunicipalityResponse;

@AllArgsConstructor
@Getter
public class District {

  private Integer id;
  private String name;
  private Integer postalCode;
  private MunicipalityResponse municipality;

  public District(Integer id) {
    this.id = id;
  }

}
