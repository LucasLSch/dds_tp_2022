package domainTests;

/*
public class OrganizationTest extends TestDataFill {
  private Organization dummyOrganization;
  private Location dummyLocation;
  private Sector dummySector;
  private Member dummyMember;
  private Member anotherDummyMember;

  @BeforeEach
  public void beforeTest() {
    this.dummyLocation = new Location(new District(100), "Cool Street", "300");
    this.dummyOrganization = new Organization("Cool Org", dummyLocation, "10/10", OrgType.ONG);
    this.dummySector = new Sector("Cool Sector", dummyOrganization);
    this.dummyMember = new Member("Roberto", "Gomez", DocType.ID, "28.375.012");
    this.anotherDummyMember = new Member("Juan Carlos", "Rodriguez", DocType.ID, "30.103.094");
  }

  @Test
  public void organizationRegistersASector() {
    dummyOrganization.registerSector(dummySector);
    assertTrue(dummyOrganization.sectorIsRegistered(dummySector));
  }

  @Test
  public void memberCorrectlyLinksToSector() {
    dummyMember.applyToSector(dummySector);
    assertTrue(dummyMember.worksIn(dummyOrganization));
    assertTrue(dummyMember.worksIn(dummySector));
  }

  @Test
  public void membersSharejourneys() {
    Transport mockedTransport = mock(Transport.class);

    List<Leg> legs = new ArrayList<Leg>();
    legs.add(new Leg(new Location(new District(1), "", ""),
        new Location(new District(1), "", ""), mockedTransport));
    legs.add(new Leg(new Location(new District(1), "", ""),
        new Location(new District(1), "", ""), mockedTransport));
    Journey dummyJourney = new Journey(legs);

    when(mockedTransport.isShareable()).thenReturn(true);

    dummyMember.applyToSector(dummySector);
    anotherDummyMember.applyToSector(dummySector);

    dummyMember.addSharedJourney(dummyJourney, anotherDummyMember);

    assertTrue(dummyMember.getJourneys().equals(anotherDummyMember.getJourneys()));
  }
}
*/