package ddsutn.domain.journey.transport;

import ddsutn.domain.location.Distance;
import ddsutn.domain.location.Location;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "stop")
public class Stop {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "location_id")
  private Location location;

  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "km_to_next_stop"))
  private Distance distanceToNextStop;

  public Stop(Location location, Distance distanceToNextStop) {
    this.location = location;
    this.distanceToNextStop = distanceToNextStop;
  }

  public void setDistanceToNextStop(Distance distanceToNextStop) {
    this.distanceToNextStop = distanceToNextStop;
  }

  public Distance getDistanceToNextStop() {
    return distanceToNextStop;
  }

}
