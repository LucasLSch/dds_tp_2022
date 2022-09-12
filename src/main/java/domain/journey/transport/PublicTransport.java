package domain.journey.transport;

import domain.location.Distance;
import domain.location.Location;
import lombok.Getter;

@Getter
public class PublicTransport extends Transport {

  private Line line;
  private Stop startingStop;
  private Stop endingStop;

  public PublicTransport(Double fuelConsumptionPerKm, Line line, Stop startingStop, Stop endingStop) {
    super(fuelConsumptionPerKm);
    this.line = line;
    this.startingStop = startingStop;
    this.endingStop = endingStop;
  }

  @Override
  public Boolean isShareable() {
    return false;
  }
  
  @Override
  public Distance getDistance(Location start, Location end) {
    //TODO validate startStop has startLocation
    //TODO validate endStop has endLocation
    return line.getDistanceBetween(this.startingStop, this.endingStop);
  }

}
