package domain.journey.transport;

import lombok.Getter;

public class HiredService extends Transport {

  @Getter
  private HiredServiceType hsType;
  @Getter
  private String serviceName;

  public HiredService(Double fuelConsumptionPerKm, HiredServiceType hsType, String serviceName) {
    super(fuelConsumptionPerKm);
    this.hsType = hsType;
    this.serviceName = serviceName;
  }

  @Override
  public Boolean isShareable() {
    return true;
  }
}
