package domain.organization;

public class Sector {

  private String sectorName;

  public Organization getOrganization() {
    return organization;
  }

  private Organization organization;

  public Sector(String sectorName, Organization organization) {
    this.sectorName = sectorName;
    this.organization = organization;
  }

  public String getSectorName() {
    return sectorName;
  }

  public void registerMember(Member member) {
    this.organization.registerMember(member, this);
  }


  public Boolean belongsTo(Organization organization) {
    return this.organization.equals(organization);
  }
}