package domain.organization;

import java.util.ArrayList;
import java.util.List;

public class Sector {

  private String sectorName;

  public Organization getOrganization() {
    return organization;
  }

  private Organization organization;
  private List<Member> memberList;

  public Sector(String sectorName, Organization organization) {
    this.sectorName = sectorName;
    this.organization = organization;
    this.memberList = new ArrayList<>();
  }

  public String getSectorName() {
    return sectorName;
  }

  public void registerMember(Member someMember) {
    if(this.organization.approvesMember(someMember, this)) {
      if (this.isNewMember(someMember)) {
        this.memberList.add(someMember);
        someMember.addSector(this);
      }
    }
  }

  private Boolean isNewMember(Member someMember) {
    return !this.memberList.contains(someMember);
  }


  public Boolean belongsTo(Organization organization) {
    return this.organization.equals(organization);
  }

  public List<Member> getMemberList() {
    return memberList;
  }

}