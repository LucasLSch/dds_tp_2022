package domain.journey.transport;

import domain.location.Location;
import lombok.AllArgsConstructor;
import services.georef.DistanceResponse;

@AllArgsConstructor
public class PublicTransport extends Transport {

  private Line line;
  private Stop startStop;
  private Stop endStop;
  
  @Override
  public Boolean isShareable() {
    return false;
  }
  
  @Override
  public DistanceResponse getDistance(Location start, Location end) {
    //TODO validate startStop has startLocation
    //TODO validate endStop has endLocation
    return line.getDistanceBetween(this.startStop, this.endStop);
  }
}
