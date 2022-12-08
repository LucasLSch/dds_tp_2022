package ddsutn.dtos.member;

import ddsutn.domain.journey.Leg;
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
  public String hfType;
  public String hfName;
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

  public LegForView(String startingLocation, String endingLocation, String transportType) {
    this.startingLocation = startingLocation;
    this.endingLocation = endingLocation;
    this.transportType = transportType;
  }
}
