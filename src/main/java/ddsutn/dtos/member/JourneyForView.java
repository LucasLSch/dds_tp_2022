package ddsutn.dtos.member;

import ddsutn.domain.journey.Journey;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class JourneyForView {
  public String id;
  public String startingLocation;
  public String endingLocation;
  public List<LegForView> legs;
  public List<String> members;

  public JourneyForView(Journey someJourney) {
    Long possibleId = someJourney.getId();
    if(possibleId == null) this.id = "";
    else this.id = possibleId.toString();
    this.startingLocation = someJourney.getStartingLocation().print();
    this.endingLocation = someJourney.getEndingLocation().print();
    this.legs = someJourney.getLegList().stream().map(LegForView::new).collect(Collectors.toList());
    this.members = someJourney.getLegList().stream().map(Objects::toString).collect(Collectors.toList());
  }
}
