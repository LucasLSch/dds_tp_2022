package domain.journey;

import domain.exceptions.EmptyJourneyException;
import domain.exceptions.NotShareableJourneyException;
import domain.exceptions.NotSharedOrganizationException;
import domain.location.Distance;
import domain.location.Location;
import domain.measurements.ActivityData;
import domain.measurements.CarbonFootprint;
import domain.measurements.unit.UnitExpression;
import domain.organization.Member;
import domain.organization.Organization;
import lombok.Getter;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class Journey {

  private Location startingLocation;
  private Location endingLocation;
  private List<Leg> legList;
  private Set<Member> members;

  // --- Constructor --- //
  public Journey(List<Leg> someLegList) {
    if (someLegList.isEmpty()) {
      throw new EmptyJourneyException();
    }
    this.legList = someLegList;
    this.updateEndLocation();
    this.updateStartLocation();
    this.members = new HashSet<>();
  }

  private void updateStartLocation() {
    Leg firstLeg = this.legList.get(0);
    this.startingLocation = firstLeg.getStartingLocation();
  }

  private void updateEndLocation() {
    Leg lastLeg = this.legList.get(legList.size() - 1);
    this.endingLocation = lastLeg.getEndingLocation();
  }

  public void addLeg(Leg someLeg) {
    this.legList.add(someLeg);
  }

  public void addMember(Member someMember) {
    if(someMember.hasJourney(this)) {
      this.members.add(someMember);
    }
  }

  public Integer membersAmount() {
    return this.members.size();
  }

  public Boolean involvesOrganization(Organization someOrganization) {
    return this.startingLocation.equals(someOrganization.getLocation())
        || this.endingLocation.equals(someOrganization.getLocation());
  }

  public Boolean isShareable() {
    return legList.stream().allMatch(Leg::isShareable);
  }

  public void beSharedWith(Member someMember) {
    try {
      this.validateOrganizationForSharing(someMember);
      this.validateSharableJourney();
    }
    catch (NotSharedOrganizationException exception) {
      System.out.println("WARN: Members does not work for the same Organization");
      return;
    } catch (NotShareableJourneyException exception) {
      System.out.println("WARN: Journey has Legs that are not shareable");
      return;
    }
    someMember.addJourney(this);
  }

  public void validateOrganizationForSharing(Member someMember) {
    if (someMember.getOrganizations().stream().anyMatch(this::involvesOrganization)) {
      throw new NotSharedOrganizationException();
    }
  }

  public void validateSharableJourney() {
    if (!this.isShareable()) {
      throw new NotShareableJourneyException();
    }
  }

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

  public CarbonFootprint getCarbonFootprint(UnitExpression someUnitExpression) {
    CarbonFootprint journeyCF = CarbonFootprint
        .sum(someUnitExpression, this.getDataActivities()
        .stream()
        .map(da -> da.getCarbonFootprint(someUnitExpression))
        .toArray(CarbonFootprint[]::new));

    journeyCF.multiplyValue(1d/this.membersAmount());
    return journeyCF;
  }

  public List<ActivityData> getDataActivities() {
    return this.legList.stream()
        .map(Leg::createDataActivities)
        .collect(Collectors.toList());
  }

}
