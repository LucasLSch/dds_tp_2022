package domain.measurements;

import domain.measurements.unit.Unit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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

  public CarbonFootprint getCarbonFootprint(Unit someUnit) {
    Double value = this.getConsumptionValue() * this.getConsumptionType().getEmissionFactorValue();
    Unit unit = this.consumptionType.getUnit();
    return new CarbonFootprint(value, unit, null).getOn(someUnit);
  }
}
