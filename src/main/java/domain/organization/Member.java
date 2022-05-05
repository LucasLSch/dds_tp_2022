package domain.organization;

import domain.journey.Journey;
import java.util.List;


public class Member {

  private List<Sector> sectorList;
  private Journey outwardJourney;
  private Journey returnJourney;

  private String name;
  private String lastName;
  private DocType docType;
  private String document;

}