package domainTests;

import ddsutn.domain.journey.Journey;
import ddsutn.domain.journey.Leg;
import ddsutn.domain.journey.transport.EcoFriendly;
import ddsutn.domain.journey.transport.HiredService;
import ddsutn.domain.journey.transport.Transport;
import ddsutn.domain.location.Distance;
import ddsutn.domain.location.District;
import ddsutn.domain.location.Location;
import ddsutn.domain.measurements.CarbonFootprint;
import ddsutn.domain.measurements.ConsumptionType;
import ddsutn.domain.measurements.EmissionFactor;
import ddsutn.domain.measurements.unit.Proportionality;
import ddsutn.domain.organization.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JourneyTest extends TestDataFill {
  private Journey dummyJourney;
  private final List<Leg> dummyLegs = new ArrayList<>();
  private Leg dummyLeg;
  private Leg anotherDummyLeg;
  private Location dummyStartLocation;
  private Location dummyEndLocation;
  private Location anotherDummyStartLocation;
  private Location anotherDummyEndLocation;
  private Transport mockedEcoFriendlyTransport;
  private Transport mockedHiredServiceTransport;

  @BeforeEach
  public void beforeTest() {
    mockedEcoFriendlyTransport = mock(EcoFriendly.class);
    mockedHiredServiceTransport = mock(HiredService.class);

    dummyStartLocation = new Location(new District(200), "Cool Start Street", "300");
    dummyEndLocation = new Location(new District(200), "Cool End Street", "700");
    anotherDummyStartLocation = new Location(new District(200), "Not So Cool Start Street", "100");
    anotherDummyEndLocation = new Location(new District(200), "Not So Cool End Street", "500");
    dummyLeg = new Leg(dummyStartLocation, dummyEndLocation, mockedEcoFriendlyTransport);
    anotherDummyLeg = new Leg(anotherDummyStartLocation, anotherDummyEndLocation, mockedHiredServiceTransport);
    dummyLegs.add(dummyLeg);
    dummyLegs.add(anotherDummyLeg);
    dummyJourney = new Journey(dummyLegs);
  }

  @Test
  public void journeyTotalDistance() throws IOException {

    when(mockedEcoFriendlyTransport.getDistance(dummyStartLocation, dummyEndLocation))
        .thenReturn(new Distance(500d, kilometer(Proportionality.DIRECT)));
    when(mockedHiredServiceTransport.getDistance(anotherDummyStartLocation, anotherDummyEndLocation))
        .thenReturn(new Distance(250d, kilometer(Proportionality.DIRECT)));

    Distance distance = dummyJourney.getJourneyDistance();

    assertEquals(distance.getValue(), 750);
  }

  @Test
  public void journeysCanDeleteMembers() {
    Member dummyMember = new Member(null,
        null,
        null,
        null
    );
    dummyMember.addJourney(dummyJourney);
    dummyJourney.addMember(dummyMember);
    dummyJourney.deleteMember(dummyMember);
    assertTrue(dummyJourney.getMembers().isEmpty());
  }

  @Test
  public void journeysCanCalculateItsCf() throws IOException {
    when(mockedEcoFriendlyTransport.getConsumption(
        dummyStartLocation,
        dummyEndLocation)
    ).thenReturn(0d);
    when(mockedEcoFriendlyTransport.getConsumptionType()
    ).thenReturn(new ConsumptionType(
        "",
        CarbonFootprint.getDefaultUnit(),
        "",
        "",
        new EmissionFactor(0d, CarbonFootprint.getDefaultUnit())));
    when(mockedHiredServiceTransport.getConsumption(
        anotherDummyStartLocation,
        anotherDummyEndLocation)
    ).thenReturn(80d);
    when(mockedHiredServiceTransport.getConsumptionType()
    ).thenReturn(new ConsumptionType(
        "",
        CarbonFootprint.getDefaultUnit(),
        "",
        "",
        new EmissionFactor(20d, CarbonFootprint.getDefaultUnit())));

    Member dummyMember = new Member(
        null,
        null,
        null,
        null
    );
    dummyMember.addJourney(dummyJourney);
    dummyJourney.addMember(dummyMember);
    CarbonFootprint cf = dummyJourney.getCarbonFootprint();
    assertEquals(cf.getValue(), 1600d);
  }

}
