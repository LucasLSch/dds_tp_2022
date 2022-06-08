package domain.journey.transport;

import API.georef.Distance;
import domain.location.Location;

public class PublicTransport extends Transport {

  private Line line;
  private Stop startStop;
  private Stop endStop;

  @Override
  public Distance getDistance(Location start, Location end) {
    //TODO validate startStop has startLocation
    //TODO validate endStop has endLocation
    return line.getDistanceBetween(this.startStop, this.endStop);
  }
}
