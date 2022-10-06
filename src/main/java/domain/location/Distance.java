package domain.location;

import domain.measurements.unit.Unit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Embeddable
public class Distance {

  private Integer value;
  private Unit unit;

}
