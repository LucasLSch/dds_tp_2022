package ddsutn.domain.location;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Municipality {

  private Integer id;
  private String name;
  private Province province;

}
