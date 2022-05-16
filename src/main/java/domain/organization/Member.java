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

  public Member() {
    this.sectorList = new ArrayList<>();
    this.journeyList = new ArrayList<>();
  }

  public void linkSector(Sector aSector) {
    aSector.acceptMember(this);
    this.sectorList.add(aSector);
  }

}