package domain.measurements.unit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SimpleUnit {

  private BaseUnit baseUnit;
  private Integer exponent;

}
