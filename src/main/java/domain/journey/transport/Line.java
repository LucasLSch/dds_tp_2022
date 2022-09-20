package domain.journey.transport;

import domain.exceptions.IncompleteLineException;
import domain.exceptions.InvalidStopForLineException;
import domain.location.Distance;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "line")
public class Line {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany(mappedBy = "line", cascade = CascadeType.ALL)
  @OrderColumn(name = "stop_number")
  private List<Stop> stopList;

  @Column(name = "name")
  private String name;

  @Enumerated(value = EnumType.STRING)
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
        .subList(this.stopList.indexOf(startStop), this.stopList.indexOf(endStop));

    Integer finalValue = stopsBetween
        .stream()
        .map(Stop::getDistanceToNextStop)
        .mapToInt(Distance::getValue)
        .sum();

    return new Distance(finalValue, "KM");
  }
}
