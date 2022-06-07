package domain.journey.transport;

import domain.location.Location;

public interface Transport {
  public Integer getDistance(Location start, Location end);
}
