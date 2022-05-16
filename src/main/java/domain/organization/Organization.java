package domain.organization;

import java.util.ArrayList;
import java.util.List;

public class Organization {

  private List<Member> memberList;
  private List<Sector> sectorList;
  private String socialObjective;
  private String location;
  private String clasification;
  private OrgType orgType;

  public Organization() {
    this.memberList = new ArrayList<>();
    this.sectorList = new ArrayList<>();
  }

  public Organization(String socObj, String locat, String clasific, OrgType orgType) {
    this.socialObjective = socObj;
    this.location = locat;
    this.clasification = clasific;
    this.orgType = orgType;
    this.memberList = new ArrayList<>();
    this.sectorList = new ArrayList<>();
  }

  public void registerSector(Sector aSector) {
    //this.validateSector(aSector) por si es necesario en un futuro
    this.sectorList.add(aSector);
  }

  public void acceptMember(Member member) {
    //TODO criterios de aceptacion o rechazo
    if(this.isNewMember(member))  this.registerMember(member);
  }

  private void registerMember(Member aMember) {
    this.memberList.add(aMember);
  }

  private Boolean isNewMember(Member aMember) {
    return !this.memberList.contains(aMember);
  }
}