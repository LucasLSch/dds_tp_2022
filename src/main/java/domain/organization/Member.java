package domain.organization;

import domain.journey.Journey;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;

public class Member implements Visitado{

  private Set<Sector> sectorList;
  @Getter
  private List<Journey> journeyList;

  private String name;
  private String lastName;
  private DocType docType;
  private String document;

  public Member(String name, String lastName, DocType docType, String document) {
    this.name = name;
    this.lastName = lastName;
    this.docType = docType;
    this.document = document;
    this.sectorList = new HashSet<>();
    this.journeyList = new ArrayList<>();
  }

  public void aceptarVisitor(VisitorHc unVisitor) {
    unVisitor.calculateHCMember(this);
  }
  public void linkSector(Sector someSector) {
    someSector.registerMember(this);
  }

  public void addSector(Sector someSector) {
    this.sectorList.add(someSector);
  }

  public void addJourney(Journey someJourney) {
    this.journeyList.add(someJourney);
  }

  public void addSharedJourney(Journey someJourney, Member someMember) {
    try {
      this.memberOrgValidation(someMember);
    } catch (RuntimeException exception) {
      System.out.println("WARN: Members does not work for the same Organization");
      return;
    }
    try {
      someJourney.isJourneyShareable();
    } catch (RuntimeException exception) {
      System.out.println("WARN: Journey has Legs that are not shareable");
      return;
    }
    this.addJourney(someJourney);
    someMember.addJourney(someJourney);
  }

  public void memberOrgValidation(Member someMember) {
    if (!this.memberSharesOrg(someMember)) {
      throw new RuntimeException("Members do not share org.");
    }
  }

  public Boolean memberSharesOrg(Member someMember) {
    return this
        .sectorList
        .stream()
        .anyMatch(sector -> someMember.worksIn(sector.getOrganization()));
  }

  public Boolean worksIn(Sector someSector) {
    return this.sectorList.contains(someSector);
  }

  public Boolean worksIn(Organization someOrganization) {
    return this.sectorList.stream().anyMatch(sector -> sector.belongsTo(someOrganization));
  }
}