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

  private Location start;
  private Location end;
  private Transport transport;
  private Integer orderInList;

  public Leg(Location someStartLocation, Location someEndLocation, Transport someTransport) {
    this.start = someStartLocation;
    this.end = someEndLocation;
    this.transport = someTransport;
  }

  public void setOrderInList(Integer orderInList) {
    this.orderInList = orderInList;
  }

  public ActivityData createDataActivities() {
    Integer consumption = null;
    try {
      consumption = this.transport.getConsumption(this.start, this.end);
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

  public Boolean transportIsShareable() {
    return transport.isShareable();
  }

  public Integer getOrderInList() {
    return orderInList;
  }

  public Distance getLegDistance() throws IOException {
    return this.transport.getDistance(this.getStart(), this.getEnd());
  }

}
