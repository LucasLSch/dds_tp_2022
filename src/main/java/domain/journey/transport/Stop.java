package domain.journey.transport;

import domain.location.Location;

public class Stop {

  private Line line;
  private Location location;
  private Integer distanceToNextStop;
  private Integer orderInList;

  public Stop(Location someLocation) {
    this.location = someLocation;
  }

  public Stop(Line someLine, Location someLocation) {
    this.line = someLine;
    this.location = someLocation;
  }

  public void setDistanceToNextStop(Integer distanceToNextStop) {
    this.distanceToNextStop = distanceToNextStop;
  }

  public Integer getOrderInList() {
    return orderInList;
  }

  public Integer getDistanceToNextStop() {
    return distanceToNextStop;
  }

  public Boolean belongsToLine(Line someLine) {
    return this.line.equals(someLine);
  }

  public void linkToLine(Line someLine) {
    this.line = someLine;
  }
}
