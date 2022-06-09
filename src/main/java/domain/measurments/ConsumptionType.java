package domain.measurments;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConsumptionType {

  private String name;
  private String unit;
  private String activity;
  private String scope;
  private EmissionFactor emissionFactor;

}
