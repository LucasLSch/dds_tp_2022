package domain.measurements;

import domain.measurements.unit.UnitExpression;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ActivityData {

  private ConsumptionType consumptionType;
  private Integer consumptionValue;
  private PeriodicityFormat periodicityFormat;
  private String periodicity;

  public LocalDate getPeriodicityDate() {
    return this.periodicityFormat.getDate(this.periodicity);
  }

  public CarbonFootprint getCarbonFootprint(UnitExpression someUnitExpression) {
    Double value = this.getConsumptionValue() * this.getConsumptionType().getEmissionFactorValue();
    UnitExpression unitExpression = this.consumptionType.getUnitExpression();
    return new CarbonFootprint(value, unitExpression, null).getOn(someUnitExpression);
  }
}
