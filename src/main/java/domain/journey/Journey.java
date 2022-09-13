package domain.journey;

import domain.exceptions.EmptyJourneyException;
import domain.exceptions.NotShareableJourneyException;
import domain.location.Distance;
import domain.location.Location;
import domain.measurements.ActivityData;
import domain.measurements.CarbonFootprint;
import domain.measurements.unit.UnitExpression;
import domain.organization.Organization;
import lombok.Getter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class Journey {

  private Location startingLocation;
  private Location endingLocation;
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
    this.startingLocation = firstLeg.getStartingLocation();
  }

  private void updateEndLocation() {
    Leg lastLeg = this.legList.get(legList.size() - 1);
    this.endingLocation = lastLeg.getEndingLocation();
  }

  public void isJourneyShareable() {
    if (!legList.stream().allMatch(Leg::transportIsShareable)) {
      throw new NotShareableJourneyException();
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

  public CarbonFootprint getCarbonFootprint(UnitExpression someUnitExpression) {
    return CarbonFootprint.sum(someUnitExpression, this.getDataActivities()
        .stream()
        .map(da -> da.getCarbonFootprint(someUnitExpression))
        .toArray(CarbonFootprint[]::new));
  }

  public List<ActivityData> getDataActivities() {
    return this.legList.stream()
        .map(Leg::createDataActivities)
        .collect(Collectors.toList());
  }

  public Boolean isJourneyFrom(Organization someOrganization) {
    return this.startingLocation.equals(someOrganization.getLocation())
        || this.endingLocation.equals(someOrganization.getLocation());
  }
}
