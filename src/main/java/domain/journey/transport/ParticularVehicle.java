package domain.journey.transport;

import domain.location.Location;

public class ParticularVehicle implements Transport {

  private ParticularVehicleType type;
  private Fuel fuel;

  @Override
  public Integer getDistance(Location start, Location end) {
    return start.getDistanceTo(end);
  }
}
