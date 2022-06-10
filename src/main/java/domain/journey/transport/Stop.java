package domain.journey.transport;

import domain.journey.Distance;
import domain.location.Location;

public class Stop {

  private Line line;
  private Location location;
  private Distance distanceToNextStop;
  private Integer orderInList;

  public Stop(Location someLocation) {
    this.location = someLocation;
  }

  public Stop(Line someLine, Location someLocation) {
    this.line = someLine;
    this.location = someLocation;
  }

  public Stop(Location someLocation, Distance distance, Integer orderInList) {
    this.location = someLocation;
    this.distanceToNextStop = distance;
    this.orderInList = orderInList;
  }

  public void setDistanceToNextStop(Distance distanceToNextStop) {
    this.distanceToNextStop = distanceToNextStop;
  }

  public Integer getOrderInList() {
    return orderInList;
  }

  public Distance getDistanceToNextStop() {
    return distanceToNextStop;
  }

  public Boolean belongsToLine(Line someLine) {
    return this.line.equals(someLine);
  }

  public void linkToLine(Line someLine) {
    this.line = someLine;
  }
}
