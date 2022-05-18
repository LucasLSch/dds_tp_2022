package domain.organization;

import domain.exceptions.InvalidSectorForOrgException;
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
    this.validateSector(someSector);
    this.sectorList.add(someSector);
  }

  //Posiblemente cambie cuando haya mas validaciones
  private void validateSector(Sector someSector) {
    if (!someSector.belongsTo(this)) {
      throw new InvalidSectorForOrgException(someSector.getSectorName(), this.socialObjective);
    }
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

  public Boolean sectorIsRegistered(Sector someSector) {
    return this.sectorList.contains(someSector);
  }
}