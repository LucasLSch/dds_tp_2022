package domain.journey;

import domain.journey.transport.Transport;

public class Leg {

  private String start;
  private String end;

  private Transport transport;

  public Leg(String someStartLocation, String someEndLocation, Transport someTransport) {
    this.start = someStartLocation;
    this.end = someEndLocation;
    this.transport = someTransport;
  }

  public String getStart() {
    return this.start;
  }

  public String getEnd() {
    return this.end;
  }

  public Boolean transportIsShareable(){
    return transport.isShareable();
  }

}
