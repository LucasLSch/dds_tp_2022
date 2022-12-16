package ddsutn.domain.location;

import ddsutn.domain.measurements.unit.Unit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Embeddable
public class Distance {

  private Double value;

  @Transient
  private Unit unit;

}
