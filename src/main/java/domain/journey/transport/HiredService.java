package domain.journey.transport;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HiredService extends Transport {

  private HiredServiceType hsType;
  private String serviceName;
  private Integer consumption;

  @Override
  public Integer getConsumptionPerKm() {
    return consumption;
  }

  @Override
  public Boolean isShareable() {
    return true;
  }
}
