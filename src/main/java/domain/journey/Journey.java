package domain.journey;

import domain.exceptions.EmptyJourneyException;
import domain.location.Distance;
import domain.location.Location;
import domain.measurements.ActivityData;
import domain.measurements.ConsumptionType;
import domain.measurements.EmissionFactor;

import java.io.Console;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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

  public List<ActivityData> getDataActivities(){
    List<ActivityData> dataActivities = this.legList.stream()
        .map(leg -> leg.createDataActivities())
        .collect(Collectors.toList());
    return dataActivities;
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
