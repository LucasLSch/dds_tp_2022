package domain.location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import services.georef.ProvinceResponse;

@AllArgsConstructor
@Getter
public class Municipality {

  private Integer id;
  private String name;
  private ProvinceResponse province;

}
