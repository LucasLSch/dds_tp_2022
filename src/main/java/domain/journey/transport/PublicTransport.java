package domain.journey.transport;

import domain.location.Location;
import services.georef.Distance;

public class PublicTransport extends Transport {

  private Line line;
  private Stop startStop;
  private Stop endStop;
  
  @Override
  public Boolean isShareable() {
    return false;
  }
  
  @Override
  public Distance getDistance(Location start, Location end) {
    //TODO validate startStop has startLocation
    //TODO validate endStop has endLocation
    return line.getDistanceBetween(this.startStop, this.endStop);
  }
}
