package domain.journey.transport;

import domain.location.Location;

public class HiredService implements Transport {

  private HiredServiceType hsType;
  private String serviceName;

  public HiredService(HiredServiceType someType, String someServiceName) {
    this.hsType = someType;
    this.serviceName = someServiceName;
  }

  @Override
  public Integer getDistance(Location start, Location end) {
    return start.getDistanceTo(end);
  }
}
