package domain.organization;

import domain.journey.Journey;
import domain.measurements.CarbonFootprint;
import domain.measurements.unit.Unit;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "sector")
public class Sector {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  private Organization organization;

  @ManyToMany
  @JoinTable(
          name = "sector_member",
          joinColumns = @JoinColumn(name = "sector_id"),
          inverseJoinColumns = @JoinColumn(name = "member_id")
  )
  private Set<Member> members;

  public Sector(String sectorName, Organization organization) {
    this.name = sectorName;
    this.organization = organization;
    this.members = new HashSet<>();
  }

  public Integer membersAmount() {
    return members.size();
  }

  public Boolean hasMember(Member someMember) {
    return this.members.contains(someMember);
  }

  public void registerMember(Member someMember) {
    if (!this.hasMember(someMember) && this.organization.approvesMember(someMember, this)) {
      this.members.add(someMember);
      someMember.addSector(this);
    }
  }

  public Boolean belongsTo(Organization organization) {
    return this.organization.equals(organization);
  }

  public CarbonFootprint getAvgCfPerMember(Set<Unit> units) {
    CarbonFootprint sectorCF = getSectorCF(units);
    return new CarbonFootprint(
        sectorCF.getValue() / this.membersAmount(),
        units);
  }

  public List<Journey> getMembersJourneys() {
    return this.getMembers()
        .stream()
        .flatMap(member -> member.getJourneys().stream())
        .filter(journey -> journey.involvesOrganization(this.organization))
        .collect(Collectors.toList());
  }

  public CarbonFootprint getSectorCF(Set<Unit> units) {
    return CarbonFootprint.sum(units, getMembersJourneys()
        .stream()
        .map(journey -> journey.getCarbonFootprint(units))
        .toArray(CarbonFootprint[]::new));
  }

}