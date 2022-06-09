package domain.organization;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Sector {

  private String sectorName;

  public Organization getOrganization() {
    return organization;
  }

  private Organization organization;
  private Set<Member> memberList;

  public Sector(String sectorName, Organization organization) {
    this.sectorName = sectorName;
    this.organization = organization;
    this.memberList = new HashSet<>();
  }

  public String getSectorName() {
    return sectorName;
  }

  public void registerMember(Member someMember) {
    if (this.organization.approvesMember(someMember, this)) {
      this.memberList.add(someMember);
      someMember.addSector(this);
    }
  }

  public Boolean belongsTo(Organization organization) {
    return this.organization.equals(organization);
  }

  public Set<Member> getMemberList() {
    return memberList;
  }

}