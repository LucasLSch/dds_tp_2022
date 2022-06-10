package domain.journey.transport;

import domain.location.Location;
import services.georef.DistanceResponse;

public class Stop {

  private Line line;
  private Location location;
  private DistanceResponse distanceToNextStop;
  private Integer orderInList;

  public Stop(Location someLocation) {
    this.location = someLocation;
  }

  public Stop(Line someLine, Location someLocation) {
    this.line = someLine;
    this.location = someLocation;
  }

  public Stop(Location someLocation, DistanceResponse distance, Integer orderInList) {
    this.location = someLocation;
    this.distanceToNextStop = distance;
    this.orderInList = orderInList;
  }

  public void setDistanceToNextStop(DistanceResponse distanceToNextStop) {
    this.distanceToNextStop = distanceToNextStop;
  }

  public Integer getOrderInList() {
    return orderInList;
  }

  public DistanceResponse getDistanceToNextStop() {
    return distanceToNextStop;
  }

  public Boolean belongsToLine(Line someLine) {
    return this.line.equals(someLine);
  }

  public void linkToLine(Line someLine) {
    this.line = someLine;
  }
}
