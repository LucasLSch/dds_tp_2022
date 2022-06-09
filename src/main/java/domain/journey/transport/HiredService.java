package domain.journey.transport;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HiredService extends Transport {

  private HiredServiceType hsType;
  private String serviceName;

  @Override
  public Boolean isShareable() {
    return true;
  }
}
