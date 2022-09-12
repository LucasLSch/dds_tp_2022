package domain.location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import services.georef.CountryResponse;

@AllArgsConstructor
@Getter
public class Province {

  private Integer id;
  private String name;
  private Country country;

}
