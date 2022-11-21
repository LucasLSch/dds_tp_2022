package domainTests;

import ddsutn.domain.contact.Contact;
import ddsutn.domain.exceptions.NotJourneyOwnerException;
import ddsutn.domain.journey.Journey;
import ddsutn.domain.journey.Leg;
import ddsutn.domain.journey.transport.Transport;
import ddsutn.domain.location.District;
import ddsutn.domain.location.Location;
import ddsutn.domain.measurements.*;
import ddsutn.domain.measurements.unit.Proportionality;
import ddsutn.domain.measurements.unit.Unit;
import ddsutn.domain.organization.*;
import ddsutn.domain.organization.workApplication.WorkApplication;
import ddsutn.domain.organization.workApplication.WorkApplicationState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    this.dummyMember = new Member("Roberto", "Gomez", DocType.DNI, "28.375.012");
    this.anotherDummyMember = new Member("Juan Carlos", "Rodriguez", DocType.DNI, "30.103.094");
  }

  /*
    Organizations
   */

  @Test
  public void organizationCreatesASector() {
    dummyOrganization.createSector("Cool Sector");
    Sector sector = dummyOrganization.getSectors().stream().findAny().orElse(null);
    assertTrue(dummyOrganization.sectorIsRegistered(sector));
  }

  @Test
  public void organizationCanAddActivityData() {
    ActivityData activityData = mock(ActivityData.class);

    dummyOrganization.addActivityData(activityData);

    assertEquals(dummyOrganization.getActivitiesData().size(), 1);
  }

  @Test
  public void memberCorrectlyApplyToSector() {
    dummyOrganization.getSectors().add(dummySector);

    dummyMember.applyToSector(dummySector);
    assertEquals(dummySector.getWorkApplications().size(), 1);
  }

  @Test
  public void organizationCarbonfootprint() {
    Unit kilogram = kilogram(Proportionality.DIRECT);
    Unit liter = liter(Proportionality.INVERSE);

    Set<Unit> ctUnits = new HashSet<>();
    ctUnits.add(kilogram);
    ctUnits.add(liter);

    Unit kilowatt = kilowatt(Proportionality.DIRECT);
    Unit seconds = second(Proportionality.INVERSE);

    Set<Unit> emUnits = new HashSet<>();
    emUnits.add(kilowatt);
    emUnits.add(seconds);

    EmissionFactor dummyEmissionFactor = new EmissionFactor(4.5, emUnits);
    ConsumptionType dummyConsumptionType = new ConsumptionType("",
            ctUnits,
            "",
            "",
            dummyEmissionFactor
    );

    ActivityData activityData = new ActivityData(dummyConsumptionType,
            7.3,
            PeriodicityFormat.AAAA,
            "2022"
    );

    Journey dummyJourney = mock(Journey.class);
    Journey anotherDummyJourney = mock(Journey.class);

    when(dummyJourney.getCarbonFootprint(emUnits)).thenReturn(new CarbonFootprint(73d, emUnits));
    when(anotherDummyJourney.getCarbonFootprint(emUnits)).thenReturn(new CarbonFootprint(37d, emUnits));

    dummyMember.addJourney(dummyJourney);
    dummyMember.addJourney(anotherDummyJourney);

    dummyOrganization.getSectors().add(dummySector);
    dummyMember.applyToSector(dummySector);
    dummySector.getWorkApplications().forEach(WorkApplication::accept);

    dummyOrganization.addActivityData(activityData);

    CarbonFootprint finalCF = dummyOrganization.getTotalCarbonFootprint(emUnits);

    assertEquals(finalCF.getValue(), 142.85);
  }

  @Test
  public void organizationNotifyContact() {
    Contact contact = mock(Contact.class);
    dummyOrganization.getContacts().add(contact);

    dummyOrganization.notify("Buen dia");
    verify(contact, times(1)).notify("Buen dia");
  }

  @Test
  public void organizationNotifiesAllMembers() {
    Contact contact1 = mock(Contact.class);
    Contact contact2 = mock(Contact.class);
    dummyMember.addContact(contact1);
    anotherDummyMember.addContact(contact2);

    dummyOrganization.getSectors().add(dummySector);
    dummyMember.applyToSector(dummySector);
    anotherDummyMember.applyToSector(dummySector);
    dummySector.getWorkApplications().forEach(WorkApplication::accept);

    dummyOrganization.notifyAllMembers("Buen dia");
    verify(contact1, times(1)).notify("Buen dia");
    verify(contact2, times(1)).notify("Buen dia");
  }

  /*
    Sector
   */

  @Test
  public void sectorCanGetAvgCfPerMember() {
    Unit kilowatt = kilowatt(Proportionality.DIRECT);
    Unit seconds = second(Proportionality.INVERSE);

    Set<Unit> emUnits = new HashSet<>();
    emUnits.add(kilowatt);
    emUnits.add(seconds);

    Journey dummyJourney = mock(Journey.class);
    Journey anotherDummyJourney = mock(Journey.class);

    when(dummyJourney.getCarbonFootprint(emUnits)).thenReturn(new CarbonFootprint(73d, emUnits));
    when(anotherDummyJourney.getCarbonFootprint(emUnits)).thenReturn(new CarbonFootprint(37d, emUnits));
    when(dummyJourney.involvesOrganization(any(Organization.class))).thenReturn(true);
    when(anotherDummyJourney.involvesOrganization(any(Organization.class))).thenReturn(true);

    dummyMember.addJourney(dummyJourney);
    anotherDummyMember.addJourney(anotherDummyJourney);

    dummyMember.applyToSector(dummySector);
    anotherDummyMember.applyToSector(dummySector);
    dummySector.getWorkApplications().forEach(WorkApplication::accept);

    CarbonFootprint carbonFootprint = dummySector.getAvgCfPerMember(emUnits);
    assertEquals(carbonFootprint.getValue(), 55d);
  }

  /*
    Member
   */

  @Test
  public void memberCanVerifyIfWorksInSectorOrOrganization() {
    dummyOrganization.getSectors().add(dummySector);
    dummyMember.applyToSector(dummySector);
    anotherDummyMember.applyToSector(dummySector);

    dummySector.getWorkApplications()
            .stream()
            .filter(wa -> wa.getMember().equals(dummyMember))
            .forEach(WorkApplication::accept);
    dummySector.getWorkApplications()
            .stream()
            .filter(wa -> wa.getMember().equals(anotherDummyMember))
            .forEach(WorkApplication::reject);

    assertTrue(dummyMember.worksIn(dummySector));
    assertTrue(dummyMember.worksIn(dummyOrganization));
    assertFalse(anotherDummyMember.worksIn(dummySector));
    assertFalse(anotherDummyMember.worksIn(dummyOrganization));
  }

  @Test
  public void membersCanDeleteJourneys() {
    Journey dummyJourney = mock(Journey.class);
    dummyMember.addJourney(dummyJourney);
    dummyMember.deleteJourney(dummyJourney);
    assertTrue(dummyMember.getJourneys().isEmpty());
  }

  @Test
  public void membersCanGetTheirCf() {
    Unit kilowatt = kilowatt(Proportionality.DIRECT);
    Unit seconds = second(Proportionality.INVERSE);

    Set<Unit> emUnits = new HashSet<>();
    emUnits.add(kilowatt);
    emUnits.add(seconds);

    Journey dummyJourney = mock(Journey.class);
    Journey anotherDummyJourney = mock(Journey.class);

    when(dummyJourney.getCarbonFootprint(emUnits)).thenReturn(new CarbonFootprint(73d, emUnits));
    when(anotherDummyJourney.getCarbonFootprint(emUnits)).thenReturn(new CarbonFootprint(37d, emUnits));

    dummyMember.addJourney(dummyJourney);
    dummyMember.addJourney(anotherDummyJourney);

    CarbonFootprint cf = dummyMember.getPersonalCF(emUnits);
    assertEquals(cf.getValue(), 110d);
  }

  @Test
  public void memberCannotShareJourneysIfDoesNotHaveThem() {
    Journey dummyJourney = mock(Journey.class);
    dummyMember.shareJourneyWith(dummyJourney, anotherDummyMember);
    assertTrue(anotherDummyMember.getJourneys().isEmpty());
    assertTrue(dummyMember.getJourneys().isEmpty());
    assertThrows(NotJourneyOwnerException.class, () -> dummyMember.validateJourneyPossession(dummyJourney));
  }

  @Test
  public void membersSharejourneys() {
    Transport mockedTransport = mock(Transport.class);

    List<Leg> legs = new ArrayList<>();
    legs.add(new Leg(new Location(new District(1), "", ""),
        new Location(new District(1), "", ""), mockedTransport));
    legs.add(new Leg(new Location(new District(1), "", ""),
        new Location(new District(1), "", ""), mockedTransport));
    Journey dummyJourney = new Journey(legs);

    when(mockedTransport.isShareable()).thenReturn(true);

    dummyOrganization.getSectors().add(dummySector);

    dummyMember.applyToSector(dummySector);
    anotherDummyMember.applyToSector(dummySector);

    dummyMember.addJourney(dummyJourney);

    dummyMember.shareJourneyWith(dummyJourney, anotherDummyMember);

    assertEquals(dummyMember.getJourneys(), anotherDummyMember.getJourneys());
  }

  /*
    WorkApplication
   */

  @Test
  public void workApplicationCanCheckItsState() {
    dummyMember.applyToSector(dummySector);
    assertTrue(dummySector.getWorkApplications()
            .stream()
            .allMatch(wa -> wa.stateIs(WorkApplicationState.PENDING)));
  }
}
