package ddsutn.domain.location;

import ddsutn.domain.measurements.unit.Unit;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Embeddable
public class Distance {

  private Integer value;

  @Transient
  private Unit unit;

}
