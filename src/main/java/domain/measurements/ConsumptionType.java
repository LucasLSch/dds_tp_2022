package domain.measurements;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ConsumptionType {

  private String name;
  private String unit;
  private String activity;
  private String scope;
  private EmissionFactor emissionFactor;

  public String getUnit() {
    return this.unit;
  }

  public void setEmissionFactor(EmissionFactor someEF) {
    this.emissionFactor = someEF;
  }

  public Double getEmissionFactorValue() {
    return this.getEmissionFactor().getFactor();
  }
}
