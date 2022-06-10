package domain.journey;

import domain.journey.transport.Transport;
import domain.location.Location;
import java.io.IOException;
import services.georef.DistanceResponse;

public class Leg {

  private Location start;
  private Location end;
  private Integer orderInList;
  private Transport transport;

  public Leg(Location someStartLocation, Location someEndLocation, Transport someTransport) {
    this.start = someStartLocation;
    this.end = someEndLocation;
    this.transport = someTransport;
  }

  public Leg(Location someStartLocation, Location someEndLocation, Transport someTransport, Integer orderInList) {
    this.start = someStartLocation;
    this.end = someEndLocation;
    this.transport = someTransport;
    this.orderInList = orderInList;
  }

  public Location getStart() {
    return this.start;
  }

  public Location getEnd() {
    return this.end;
  }

  public void setOrderInList(Integer orderInList) {
    this.orderInList = orderInList;
  }

  public Boolean transportIsShareable() {
    return transport.isShareable();
  }

  public Integer getOrderInList() {
    return orderInList;
  }

  public DistanceResponse getLegDistance() throws IOException {
    return this.transport.getDistance(this.getStart(), this.getEnd());
  }

}
