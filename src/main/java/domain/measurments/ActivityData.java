package domain.measurments;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ActivityData {

  private ConsumptionType consumptionType;
  private Integer consumptionValue;
  private PeriodicityFormat periodicityFormat;
  private String periodicity;
}
