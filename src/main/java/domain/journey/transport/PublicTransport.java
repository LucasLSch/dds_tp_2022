package domain.journey.transport;

import domain.location.Distance;
import domain.location.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class PublicTransport extends Transport {

  @Getter
  private Line line;
  @Getter
  private Stop startStop;
  @Getter
  private Stop endStop;
  private Integer consumption;

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

  @Override
  public Integer getConsumptionPerKm() {
    return consumption;
  }
}
