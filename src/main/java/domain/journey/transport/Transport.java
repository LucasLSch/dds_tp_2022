package domain.journey.transport;

import domain.location.Distance;
import domain.location.Location;
import java.io.IOException;

public abstract class  Transport {

  public Distance getDistance(Location start, Location end) throws IOException {
    return start.getDistanceTo(end);
  }
  public Integer getConsumption(Location start, Location end) throws IOException {
    return this.getConsumptionPerKm() * this.getDistance(start,end).getValue();
  }

  public abstract Integer getConsumptionPerKm();

  public abstract Boolean isShareable();

}
