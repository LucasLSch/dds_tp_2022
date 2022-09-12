package domain.organization;

import domain.journey.Journey;
import domain.measurements.CarbonFootprint;
import domain.measurements.unit.UnitExpression;
import lombok.Getter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class Sector {

  private String name;
  private Organization organization;
  private Set<Member> members;

  public Sector(String sectorName, Organization organization) {
    this.name = sectorName;
    this.organization = organization;
    this.members = new HashSet<>();
  }

  public Integer membersAmount() {
    return members.size();
  }

  public String getName() {
    return name;
  }

  public void registerMember(Member someMember) {
    if (this.organization.approvesMember(someMember, this)) {
      this.members.add(someMember);
      someMember.addSector(this);
    }
  }

  public Boolean belongsTo(Organization organization) {
    return this.organization.equals(organization);
  }

  public Set<Member> getMembers() {
    return this.members;
  }

  public CarbonFootprint getAvgCfPerMember(UnitExpression someUnitExpression) {
    CarbonFootprint sectorCF = getSectorCF(someUnitExpression);
    return new CarbonFootprint(
        sectorCF.getValue() / this.membersAmount(),
        someUnitExpression,
        LocalDate.now());
  }

  public Set<Journey> getMembersJourneys() {
    return this.getMembers()
        .stream()
        .flatMap(member -> member.getJourneys().stream())
        .collect(Collectors.toSet());
  }

  public CarbonFootprint getSectorCF(UnitExpression someUnitExpression) {
    return CarbonFootprint.sum(someUnitExpression, getMembersJourneys()
        .stream()
        .map(journey -> journey.getCarbonFootprint(someUnitExpression))
        .toArray(CarbonFootprint[]::new));
  }

}