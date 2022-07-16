package domain.measurements;

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

  public CarbonFootprint getCarbonFootprint(String someUnit) {
    Double value = this.getConsumptionValue() * this.getConsumptionType().getEmissionFactorValue();
    String unit = this.consumptionType.getUnit();
    return new CarbonFootprint(value, unit, null).getOn(someUnit);
  }
}
