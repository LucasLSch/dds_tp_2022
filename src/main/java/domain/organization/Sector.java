package domain.organization;

import java.util.HashSet;
import java.util.Set;

public class Sector implements Visitado{

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
  public Integer cantMember(){
    return members.size();
  }
  @Override
  public Double acceptVisitor(VisitorHc unVisitor) {
    return unVisitor.calculateHCSector(this,organization);
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

}