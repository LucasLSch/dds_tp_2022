package domain.journey.transport;

import lombok.Getter;

public class EcoFriendly extends Transport {

  @Getter
  private EcoFriendlyType type;

  public EcoFriendly(EcoFriendlyType type) {
    super(0d);
    this.type = type;
  }

  @Override
  public Double getFuelConsumptionPerKm() {
    return 0d;
  }

  @Override
  public Boolean isShareable() {
    return false;
  }
}
