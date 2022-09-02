package domain.journey.transport;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class EcoFriendly extends Transport {

  @Getter
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
