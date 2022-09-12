package domain.location;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class District {

  private Integer id;
  private String name;
  private Integer postalCode;
  private Municipality municipality;

  public District(Integer id) {
    this.id = id;
  }

}
