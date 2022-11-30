package ddsutn.domain.organization;

import ddsutn.domain.journey.Journey;
import ddsutn.domain.measurements.CarbonFootprint;
import ddsutn.domain.measurements.unit.Unit;
import ddsutn.domain.organization.workApplication.WorkApplication;
import ddsutn.domain.organization.workApplication.WorkApplicationState;
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

  @OneToMany(mappedBy = "sector", cascade = CascadeType.ALL)
  private Set<WorkApplication> workApplications;

  public Sector(String sectorName, Organization organization) {
    this.name = sectorName;
    this.organization = organization;
    this.members = new HashSet<>();
    this.workApplications = new HashSet<>();
  }

  public Integer membersAmount() {
    return members.size();
  }

  public Boolean hasMember(Member someMember) {
    return this.members.contains(someMember);
  }

  public void addMember(Member someMember) {
    if (!this.hasMember(someMember)) {
      this.members.add(someMember);
    }
  }

  public void createWorkApplication(Member someMember) {
    WorkApplication wa = new WorkApplication(this, someMember, WorkApplicationState.PENDING);
    this.workApplications.add(wa);
  }

  public Boolean belongsTo(Organization organization) {
    return this.organization.equals(organization);
  }

  public CarbonFootprint getAvgCfPerMember(Set<Unit> units) {
    return this.getCarbonFootprint(units).multiplyValue(1d / this.membersAmount());
  }

  public CarbonFootprint getAvgCfPerMember() {
    return this.getAvgCfPerMember(CarbonFootprint.getDefaultUnit());
  }

  public List<Journey> getMembersJourneys() {
    return this.getMembers()
        .stream()
        .flatMap(member -> member.getJourneys().stream())
        .filter(journey -> journey.involvesOrganization(this.organization))
        .collect(Collectors.toList());
  }

  public CarbonFootprint getCarbonFootprint(Set<Unit> units) {
    return CarbonFootprint.sum(units, getMembersJourneys()
        .stream()
        .map(journey -> journey.getCarbonFootprint(units))
        .toArray(CarbonFootprint[]::new));
  }

  public CarbonFootprint getSectorCarbonFootprint() {
    return getCarbonFootprint(CarbonFootprint.getDefaultUnit());
  }

}