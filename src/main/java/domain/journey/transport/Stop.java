package domain.journey.transport;

public class Stop {

  private Line line;
  private String location;

  public Stop(String someLocation) {
    this.location = someLocation;
  }

  public Stop(Line someLine, String someLocation) {
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
