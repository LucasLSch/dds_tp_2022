package domain.measurements;

import domain.measurements.unit.UnitExpression;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ConsumptionType {

  private String name;
  private UnitExpression unitExpression;
  private String activity;
  private String scope;
  private EmissionFactor emissionFactor;

  public UnitExpression getUnitExpression() {
    return this.unitExpression;
  }

  public void setEmissionFactor(EmissionFactor someEF) {
    this.emissionFactor = someEF;
  }

  public Double getEmissionFactorValue() {
    return this.getEmissionFactor().getValue();
  }
}
