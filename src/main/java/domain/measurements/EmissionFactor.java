package domain.measurements;

import domain.exceptions.InvalidUnitException;
import domain.measurements.unit.UnitExpression;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class EmissionFactor {

  private Double value;
  private UnitExpression unit;
  private ConsumptionType consumptionTypeAsociated;

  // Pensar si es acorde que se auto setee en el tipo de consumo en el momento que se crea el FE.
  private void linkTo(ConsumptionType consumptionType) {
    if (true /* TODO condicion*/) {
      this.consumptionTypeAsociated = consumptionType;
      consumptionType.setEmissionFactor(this);
    } else {
      throw new InvalidUnitException();
    }
  }

}
