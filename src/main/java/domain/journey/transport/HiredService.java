package domain.journey.transport;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class HiredService extends Transport {

  @Getter
  private HiredServiceType hsType;
  @Getter
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
