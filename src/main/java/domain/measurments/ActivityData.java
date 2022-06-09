package domain.measurments;

import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class ActivityData {

  private ConsumptionType consumptionType;
  private Integer consumptionValue;
  private PeriodicityFormat periodicityFormat;
  private String periodicity;

  public LocalDate getPeriodicityDate() {
    return this.periodicityFormat.getDate(this.periodicity);
  }
}
