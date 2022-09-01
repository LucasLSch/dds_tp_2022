package domain.organization;

import domain.journey.Journey;
import domain.measurements.CarbonFootprint;
import domain.measurements.unit.Unit;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Sector {

  private String sectorName;

  public Organization getOrganization() {
    return organization;
  }

  private Organization organization;
  private Set<Member> members;

  public Sector(String sectorName, Organization organization) {
    this.sectorName = sectorName;
    this.organization = organization;
    this.members = new HashSet<>();
  }
  public Integer membersAmount(){
    return members.size();
  }

  public String getSectorName() {
    return sectorName;
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

  public CarbonFootprint getAvgCFPerMember(Unit someUnit) {
    CarbonFootprint sectorCF = getSectorCF(someUnit);
    return new CarbonFootprint(
        sectorCF.getValue()/this.membersAmount()
        , someUnit
        , LocalDate.now());
  }

  public Set<Journey> getMembersJourneys() {
    return this.getMembers()
        .stream()
        .flatMap(member -> member.getJourneyList().stream())
        .collect(Collectors.toSet());
  }

  public CarbonFootprint getSectorCF(Unit someUnit) {
    return CarbonFootprint.sum(someUnit, getMembersJourneys()
        .stream()
        .map(journey -> journey.getCarbonFootprint(someUnit))
        .toArray(CarbonFootprint[]::new));
  }

}