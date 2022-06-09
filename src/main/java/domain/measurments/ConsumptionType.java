package domain.measurments;

import lombok.AllArgsConstructor;

@AllArgsConstructor
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
}
