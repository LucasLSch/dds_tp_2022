package domain.organization;

import domain.journey.Journey;
import java.util.ArrayList;
import java.util.List;


public class Member {

  private List<Sector> sectorList;
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
    this.sectorList = new ArrayList<>();
    this.journeyList = new ArrayList<>();
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

  public void addSharedJourney(Journey someJourney, Member someMember){
    //TODO validacion org

    someJourney.isJourneyShareable();
    this.addJourney(someJourney);
    someMember.addJourney(someJourney);
  }



  public Boolean worksIn(Sector someSector) {
    return this.sectorList.contains(someSector);
  }

  public Boolean worksIn(Organization someOrganization) {
    return this.sectorList.stream().anyMatch(sector -> sector.belongsTo(someOrganization));
  }
}