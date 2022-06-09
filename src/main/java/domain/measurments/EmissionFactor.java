package domain.measurments;

import domain.exceptions.InvalidUnitException;

public class EmissionFactor {

  private Double factor;
  private String unit;
  private ConsumptionType consumptionTypeAsociated;

  public EmissionFactor(Double factor, String unit, ConsumptionType consumptionType) {
    this.linkTo(consumptionType);
    this.factor = factor;
    this.unit = unit;
  }

  // Pensar si es acorde que se auto setee en el tipo de consumo en el momento que se crea el FE.
  private void linkTo(ConsumptionType consumptionType) {
    if (this.unit.equals(consumptionType.getUnit())) {
      this.consumptionTypeAsociated = consumptionType;
      consumptionType.setEmissionFactor(this);
    } else throw new InvalidUnitException();
  }

}
