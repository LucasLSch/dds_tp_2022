package domain.journey;

import domain.journey.transport.Transport;
import domain.location.Location;

public class Leg {

  private Location start;
  private Location end;

  private Transport transport;

  public Leg(Location someStartLocation, Location someEndLocation, Transport someTransport) {
    this.start = someStartLocation;
    this.end = someEndLocation;
    this.transport = someTransport;
  }

  public Location getStart() {
    return this.start;
  }

  public Location getEnd() {
    return this.end;
  }

}
