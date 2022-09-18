package domain.journey.transport;

import domain.location.Distance;
import domain.location.Location;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "stop")
public class Stop {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "line_id")
  private Line line;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "location_id")
  private Location location;

  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "km_to_next_stop"))
  private Distance distanceToNextStop;

  public Stop(Line line, Location location, Distance distanceToNextStop) {
    this.line = line;
    this.location = location;
    this.distanceToNextStop = distanceToNextStop;
  }

  public void setDistanceToNextStop(Distance distanceToNextStop) {
    this.distanceToNextStop = distanceToNextStop;
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
