package domain.journey.transport;

import API.georef.Distance;
import domain.exceptions.IncompleteLineException;
import domain.exceptions.InvalidStopForLineException;
import java.util.List;

public class Line {

  private List<Stop> stopList;
  private String name;
  private PublicTransportType pptype;


  public Line(List<Stop> someStopList, String someName, PublicTransportType someType) {
    this.stopList = someStopList;
    this.registerStops();
    this.validateStops();
    this.name = someName;
    this.pptype = someType;
  }

  public void registerStops() {
    this.stopList.forEach(stop -> stop.linkToLine(this));
  }

  public void validateStops() {
    this.validateStopsAmount();
    this.validateStopsLines();
  }

  public void validateStopsAmount() {
    if (this.stopList.size() < 2) {
      throw new IncompleteLineException();
    }
  }

  public void validateStopsLines() {
    for (Stop someStop : this.stopList) {
      if (!someStop.belongsToLine(this)) {
        throw new InvalidStopForLineException();
      }
    }
  }

  public void addNewStop(Stop someStop) {
    if (someStop.belongsToLine(this) && !this.stopList.contains(someStop)) {
      this.stopList.add(someStop);
    }
  }

  public Distance getDistanceBetween(Stop startStop, Stop endStop) {
    List<Stop> stopsBetween = this.stopList.subList(startStop.getOrderInList(), endStop.getOrderInList());
    int finalValue = stopsBetween.stream().map(Stop::getDistanceToNextStop).mapToInt(Distance::getValue).sum();
    return new Distance(finalValue, "KM");
  }
}
