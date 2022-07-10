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

  public Double calcularHc() {
    return this.getConsumptionValue() * this.getConsumptionType().getEmissionFactorValue();
  }
}
