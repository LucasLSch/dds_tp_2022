package domain.journey.transport;

public class Stop {

  private Line line;
  private String location;

  public Stop(Line someLine, String someLocation) {
    this.line = someLine;
    this.location = someLocation;
  }

  public Boolean belongsToLine(Line someLine) {
    return this.line.equals(someLine);
  }

}
