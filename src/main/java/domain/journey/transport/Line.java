package domain.journey.transport;

import domain.exceptions.IncompleteLineException;
import domain.exceptions.InvalidStopForLineException;
import domain.location.Distance;
import java.util.List;

public class Line {

  private List<Stop> stopList;
  private String name;
  private PublicTransportType type;

  public Line(List<Stop> someStopList, String someName, PublicTransportType someType) {
    this.stopList = someStopList;
    this.registerStops();
    this.validateStops();
    this.name = someName;
    this.type = someType;
  }

  public void registerStops() {
    this.stopList.forEach(stop -> stop.linkToLine(this));
  }

  public void validateStops() {
    this.validateStopsAmount();
    this.validateAllStopsLines();
  }

  public void validateStopsAmount() {
    if (this.stopList.size() < 2) {
      throw new IncompleteLineException();
    }
  }

  public void validateAllStopsLines() {
    if (!this.stopList.stream().allMatch(stop -> stop.belongsToLine(this))) {
      throw new InvalidStopForLineException();
    }
  }

  public void addNewStop(Stop someStop) {
    if (someStop.belongsToLine(this) && !this.stopList.contains(someStop)) {
      this.stopList.add(someStop);
    }
  }

  public Distance getDistanceBetween(Stop startStop, Stop endStop) {
    List<Stop> stopsBetween = this
        .stopList
        .subList(startStop.getOrderInList(), endStop.getOrderInList());

    Integer finalValue = stopsBetween
        .stream()
        .map(Stop::getDistanceToNextStop)
        .mapToInt(Distance::getValue)
        .sum();

    return new Distance(finalValue, "KM");
  }
}
