package OrganizationTest.Model;

import domain.organization.Member;
import domain.organization.OrgType;
import domain.organization.Organization;
import domain.organization.Sector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrganizationTest {
  private Organization dummyOrganization;
  private Sector dummySector;
  private Member dummyMember;

  @BeforeEach
  public void beforeTest() {
    this.dummyOrganization = new Organization("Cool Org", "Somewhere", "10/10", OrgType.ONG);
    this.dummySector = new Sector("Cool Sector", dummyOrganization);
    this.dummyMember = new Member();
  }

  @Test
  public void organizationCreationIsCorrect() {
    assertNotNull(dummyOrganization);
  }

  @Test
  public void sectorCreationIsCorrect() {
    assertNotNull(dummySector);
    assertTrue(dummyOrganization.sectorIsRegistered(dummySector));
  }

  @Test
  public void memberCreationIsCorrect() {
    assertNotNull(dummyMember);
  }

  @Test
  public void memberCorrectlyLinksToSector() {
    dummyMember.linkSector(dummySector);
    assertTrue(dummyMember.worksIn(dummyOrganization));
    assertTrue(dummyMember.worksIn(dummySector));
  }
}
