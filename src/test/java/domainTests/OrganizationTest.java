package domainTests;

import domain.location.Location;
import domain.organization.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrganizationTest {
  private Organization dummyOrganization;
  private Location dummyLocation;
  private Sector dummySector;
  private Member dummyMember;

  @BeforeEach
  public void beforeTest() {
    this.dummyLocation = new Location(100, "Cool Street", "300");
    this.dummyOrganization = new Organization("Cool Org", dummyLocation, "10/10", OrgType.ONG);
    this.dummySector = new Sector("Cool Sector", dummyOrganization);
    this.dummyMember = new Member("Roberto", "Gomez", DocType.ID, "28.375.012");
  }

  @Test
  public void organizationRegistersASector() {
    dummyOrganization.registerSector(dummySector);
    assertTrue(dummyOrganization.sectorIsRegistered(dummySector));
  }

  @Test
  public void memberCorrectlyLinksToSector() {
    dummyMember.linkSector(dummySector);
    assertTrue(dummyMember.worksIn(dummyOrganization));
    assertTrue(dummyMember.worksIn(dummySector));
  }
}