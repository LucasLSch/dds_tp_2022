package domain.journey.transport;

import domain.location.Location;

public class EcoFriendly implements Transport {

  private EcoFriendlyType efType;

  @Override
  public Integer getDistance(Location start, Location end) {
    return start.getDistanceTo(end);
  }
}
