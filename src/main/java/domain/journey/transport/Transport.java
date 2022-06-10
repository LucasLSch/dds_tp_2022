package domain.journey.transport;

import domain.journey.Distance;
import domain.location.Location;
import java.io.IOException;

public abstract class Transport {

  public Distance getDistance(Location start, Location end) throws IOException {
    return start.getDistanceTo(end);
  }
  
  public abstract Boolean isShareable();

}
