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

  public void registerSector(Sector someSector) {
    //this.validateSector(someSector) por si es necesario en un futuro
    this.sectorList.add(someSector);
  }

  public void acceptMember(Member someMember, Sector someSector) {
    //TODO this.validate(someMember, someSector) //Criterios desconocidos
    this.registerMember(someMember, someSector);
  }

  private void registerMember(Member someMember, Sector someSector) {
    if (this.isNewMember(someMember)) {
      this.memberList.add(someMember);
    }
    someMember.addSector(someSector);
  }

  private Boolean isNewMember(Member someMember) {
    return !this.memberList.contains(someMember);
  }

}