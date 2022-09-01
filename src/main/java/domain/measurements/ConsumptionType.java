package domain.measurements;

import domain.measurements.unit.Unit;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ConsumptionType {

  private String name;
  private Unit unit;
  private String activity;
  private String scope;
  private EmissionFactor emissionFactor;

  public Unit getUnit() {
    return this.unit;
  }

  public void setEmissionFactor(EmissionFactor someEF) {
    this.emissionFactor = someEF;
  }

  public Double getEmissionFactorValue() {
    return this.getEmissionFactor().getFactor();
  }
}
