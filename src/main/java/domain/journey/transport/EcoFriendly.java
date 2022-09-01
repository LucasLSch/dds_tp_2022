package domain.journey.transport;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EcoFriendly extends Transport {

  private EcoFriendlyType efType;
  private Integer consumption;

  @Override
  public Integer getConsumptionPerKm() {
    return consumption;
  }

  @Override
  public Boolean isShareable() {
    return false;
  }
}
