package ddsutn.domain.journey.transport;

import ddsutn.domain.exceptions.IncompleteLineException;
import ddsutn.domain.location.Distance;
import ddsutn.domain.measurements.unit.BaseUnit;
import ddsutn.domain.measurements.unit.Proportionality;
import ddsutn.domain.measurements.unit.Unit;
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

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "line_id")
  @OrderColumn(name = "stop_number")
  private List<Stop> stopList;

  @Column(name = "name")
  private String name;

  @Enumerated(value = EnumType.STRING)
  private PublicTransportType type;

  public Line(List<Stop> someStopList, String someName, PublicTransportType someType) {
    this.stopList = someStopList;
    this.validateStopsAmount();
    this.name = someName;
    this.type = someType;
  }

  public void validateStopsAmount() {
    if (this.stopList.size() < 2) {
      throw new IncompleteLineException();
    }
  }

  public void addNewStop(Stop someStop) {
    if (!this.stopList.contains(someStop)) {
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

    return new Distance(finalValue, new Unit(BaseUnit.METER, 3, Proportionality.DIRECT));
  }
}
