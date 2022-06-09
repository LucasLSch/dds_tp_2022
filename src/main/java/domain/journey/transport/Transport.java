package domain.journey.transport;

import domain.location.Location;
import java.io.IOException;
import services.georef.Distance;

public abstract class Transport {

  public Distance getDistance(Location start, Location end) throws IOException {
    return start.getDistanceTo(end);
  }
  
  public abstract Boolean isShareable();

}
