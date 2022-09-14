package domain.journey;

import domain.journey.transport.Transport;
import domain.location.Distance;
import domain.location.Location;
import domain.measurements.ActivityData;
import domain.measurements.ConsumptionType;
import domain.measurements.PeriodicityFormat;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Leg {

  private Location startingLocation;
  private Location endingLocation;
  private Transport transport;
  private Integer orderInList;

  public Leg(Location someStartLocation, Location someEndLocation, Transport someTransport) {
    this.startingLocation = someStartLocation;
    this.endingLocation = someEndLocation;
    this.transport = someTransport;
  }

  public void setOrderInList(Integer orderInList) {
    this.orderInList = orderInList;
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

  public Integer getOrderInList() {
    return orderInList;
  }

  public Distance getLegDistance() throws IOException {
    return this.transport.getDistance(this.getStartingLocation(), this.getEndingLocation());
  }

}
