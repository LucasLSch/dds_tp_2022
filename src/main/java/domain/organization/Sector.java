package domain.organization;

public class Sector {

  private String sectorName;
  private Organization organization;

  public Sector(String sectorName, Organization organization) {
    this.sectorName = sectorName;
    this.organization = organization;
  }
}
