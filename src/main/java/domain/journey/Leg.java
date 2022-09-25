package domain.journey;

import domain.journey.transport.Transport;
import domain.location.Distance;
import domain.location.Location;
import domain.measurements.ActivityData;
import domain.measurements.ConsumptionType;
import domain.measurements.PeriodicityFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "leg")
public class Leg {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "starting_location_id")
  private Location startingLocation;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "ending_location_id")
  private Location endingLocation;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "transport_id")
  private Transport transport;

  public Leg(Location someStartLocation, Location someEndLocation, Transport someTransport) {
    this.startingLocation = someStartLocation;
    this.endingLocation = someEndLocation;
    this.transport = someTransport;
  }

  public ActivityData createDataActivities() {
    Double consumption = null;
    try {
      consumption = this.transport.getConsumption(this.startingLocation, this.endingLocation);
    } catch (IOException io) {
      System.out.println("IO ERROR");
      return null;
    }
    ConsumptionType consumptionType = this.transport.getConsumptionType();
    return new ActivityData(consumptionType,
        consumption,
        PeriodicityFormat.MMAAAA,
        LocalDate.now().format(DateTimeFormatter.ofPattern("MM/yyyy")));
  }

  public Boolean isShareable() {
    return transport.isShareable();
  }

  public Distance getLegDistance() throws IOException {
    return this.transport.getDistance(this.getStartingLocation(), this.getEndingLocation());
  }

}
