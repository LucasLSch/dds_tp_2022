package ddsutn.domain.journey.transport;

import ddsutn.domain.location.Distance;
import ddsutn.domain.location.Location;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@DiscriminatorValue(value = "PUBLIC_TRANSPORT")
public class PublicTransport extends Transport {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "pt_line_id")
  private Line line;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "pt_starting_stop_id")
  private Stop startingStop;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "pt_ending_stop_id")
  private Stop endingStop;

  public PublicTransport(Double fuelConsumptionPerKm,
                         Line line,
                         Stop startingStop,
                         Stop endingStop) {
    super(fuelConsumptionPerKm);
    this.line = line;
    this.startingStop = startingStop;
    this.endingStop = endingStop;
  }

  @Override
  public Boolean isShareable() {
    return false;
  }

  @Override
  public String print() {
    return this.line.print();
  }

  @Override
  public Distance getDistance(Location start, Location end) {
    //TODO validate startStop has startLocation
    //TODO validate endStop has endLocation
    return line.getDistanceBetween(this.startingStop, this.endingStop);
  }

}
