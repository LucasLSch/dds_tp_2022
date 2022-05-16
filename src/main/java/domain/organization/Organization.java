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

  public Organization(String socObj, String locat, String clasific, OrgType orgType) {
    this.socialObjective = socObj;
    this.location = locat;
    this.clasification = clasific;
    this.orgType = orgType;
    this.memberList = new ArrayList<>();
    this.sectorList = new ArrayList<>();
  }

  public void singUpSector(Sector aSector) {
    //this.validateSector(aSector) por si es necesario en un futuro
    this.sectorList.add(aSector);
  }

}