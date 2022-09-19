package domain.location;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Province {

  private Integer id;
  private String name;
  private Country country;

}
