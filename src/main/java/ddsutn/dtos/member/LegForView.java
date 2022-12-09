package ddsutn.dtos.member;

import ddsutn.domain.journey.Leg;
import ddsutn.domain.journey.transport.*;
import ddsutn.domain.location.Location;
import ddsutn.services.LineSvc;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LegForView {

  public String startingLocation;
  public String endingLocation;
  public String transportType;
  public String efType;
  public String hsType;
  public String hsName;
  public String pvType;
  public String pvFuel;
  public String ptLineId;
  public String ptStop1;
  public String ptStop2;

  public LegForView(Leg someLeg) {
    this.startingLocation = someLeg.getStartingLocation().print();
    this.endingLocation = someLeg.getEndingLocation().print();
    this.transportType = someLeg.getTransport().print();
  }

  public Leg toLeg(LineSvc lineSvc) {

    Transport transport;

    switch(transportType) {
      case "":
        throw new RuntimeException("No se eligio un tipo de transporte");
      case "ecoFriendly":
        transport = this.createEcoFriendlyLeg();
        break;
      case "hiredService":
        transport = this.createHiredServiceLeg();
        break;
      case "particularVehicle":
        transport = this.createParticularVehicleLeg();
        break;
      case "publicTransport":
        transport = this.createPublicTransportLeg(lineSvc);
        break;
      default:
        throw new RuntimeException("No se eligio un tipo de transporte valido");
    }

    Location location1 = Location.getLocationFor(this.startingLocation);
    Location location2 = Location.getLocationFor(this.endingLocation);
    return new Leg(location1, location2, transport);
  }

  private PublicTransport createPublicTransportLeg(LineSvc lineSvc) {
    System.out.println(this.ptLineId);
    System.out.println(this.ptStop1);
    System.out.println(this.ptStop2);

    Long lineId = Long.parseLong(this.ptLineId);
    Long stop1Id = Long.parseLong(this.ptStop1);
    Long stop2Id = Long.parseLong(this.ptStop2);

    Line line = lineSvc.findById(lineId);
    Stop stop1 = line.getStopById(stop1Id);
    Stop stop2 = line.getStopById(stop2Id);

    return new PublicTransport(line, stop1, stop2);
  }

  private ParticularVehicle createParticularVehicleLeg() {
    return new ParticularVehicle(ParticularVehicleType.valueOf(this.pvType), Fuel.valueOf(this.pvFuel));
  }

  private HiredService createHiredServiceLeg() {
    return new HiredService(HiredServiceType.valueOf(this.hsType), this.hsName);
  }

  private EcoFriendly createEcoFriendlyLeg() {
    return new EcoFriendly(EcoFriendlyType.valueOf(this.efType));
  }


}
