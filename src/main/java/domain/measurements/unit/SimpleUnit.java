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
  private Proportionality proportionality;

  public Integer getProportionalityFactor() {
    return this.proportionality.getFactor();
  }

  public Integer getSignedExponent() {
    return this.getExponent() * this.getProportionalityFactor();
  }

  public Boolean isDirectlyProportional() {
    return this.proportionality.equals(Proportionality.DIRECT);
  }

}
