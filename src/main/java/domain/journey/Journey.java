package domain.journey;

import domain.exceptions.EmptyJourneyException;
import java.util.List;

public class Journey {

  private String start;
  private String end;

  private List<Leg> legList;

  public Journey(List<Leg> someLegList) {
    if (someLegList.isEmpty()) {
      throw new EmptyJourneyException();
    }
    this.legList = someLegList;
    this.updateEndLocation();
    this.updateStartLocation();
  }

  private void updateStartLocation() {
    Leg firstLeg = this.legList.get(0);
    this.start = firstLeg.getStart();
  }

  private void updateEndLocation() {
    Leg lastLeg = this.legList.get(legList.size() - 1);
    this.end = lastLeg.getEnd();
  }

  public void isJourneyShareable(){
    if(legList.stream().anyMatch(leg->!leg.transportIsShareable())) {
      throw new RuntimeException("Journey is not shareable");
    }
  }


//TODO exceptions shareable
}
