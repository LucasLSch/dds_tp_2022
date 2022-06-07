package domain.journey.transport;

import domain.location.Location;

public class Stop {

  private Line line;
  private Location location;

  public Stop(Location someLocation) {
    this.location = someLocation;
  }

  public Stop(Line someLine, Location someLocation) {
    this.line = someLine;
    this.location = someLocation;
  }

  public Boolean belongsToLine(Line someLine) {
    return this.line.equals(someLine);
  }

  public void linkToLine(Line someLine) {
    this.line = someLine;
  }
}
