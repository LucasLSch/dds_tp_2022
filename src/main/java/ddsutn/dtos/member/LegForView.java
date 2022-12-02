package ddsutn.dtos.member;

import ddsutn.domain.journey.Leg;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LegForView {
  public String startingLocation;
  public String endingLocation;
  public String transport;

  public LegForView(Leg someLeg) {
    this.startingLocation = someLeg.getStartingLocation().print();
    this.endingLocation = someLeg.getEndingLocation().print();
    this.transport = someLeg.getTransport().print();
  }
}
