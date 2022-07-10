package domain.journey;

import domain.exceptions.EmptyJourneyException;
import domain.location.Distance;
import domain.location.Location;
import java.io.IOException;
import java.util.List;

public class Journey {

  private Location start;
  private Location end;

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

  public void isJourneyShareable() {
    if (!legList.stream().allMatch(Leg::transportIsShareable)) {
      throw new RuntimeException("Journey is not shareable");
    }
  }

  //TODO exceptions shareable

  public Distance getJourneyDistance() {
    int finalDistanceValue = this.legList.stream()
        .mapToInt(leg -> {
          try {
            return leg.getLegDistance().getValue();
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        })
        .sum();

    return new Distance(finalDistanceValue, "KM");
  }

  public Distance getDistanceFromTo(Leg someLeg, Leg anotherLeg) {
    List<Leg> betweenLegs = this
        .legList
        .subList(someLeg.getOrderInList(), anotherLeg.getOrderInList() + 1);

    int finalDistanceValue = betweenLegs.stream()
        .mapToInt(leg -> {
          try {
            return leg.getLegDistance().getValue();
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        })
        .sum();

    return new Distance(finalDistanceValue, "KM");
  }

  public void addLeg(Leg someLeg) {
    this.legList.add(someLeg);
  }

}
