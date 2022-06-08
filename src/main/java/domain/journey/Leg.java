package domain.journey;

import services.georef.Distance;
import domain.journey.transport.Transport;
import domain.location.Location;

import java.io.IOException;

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

  public Location getStart() {
    return this.start;
  }

  public Location getEnd() {
    return this.end;
  }


  public Boolean transportIsShareable(){
    return transport.isShareable();
  }


  public Integer getOrderInList() {
    return orderInList;
  }

  public Distance getLegDistance() throws IOException {
    return this.transport.getDistance(this.getStart(), this.getEnd());
  }

}
