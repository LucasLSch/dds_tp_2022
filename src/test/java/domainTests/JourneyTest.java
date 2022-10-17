package domainTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class JourneyTest extends TestDataFill {
//  private Journey dummyJourney;
//  private List<Leg> dummyLegs = new ArrayList<>();
//  private Leg dummyLeg;
//  private Leg anotherDummyLeg;
//  private Location dummyStartLocation;
//  private Location dummyEndLocation;
//  private Location anotherDummyStartLocation;
//  private Location anotherDummyEndLocation;
//  private Transport mockedEcoFriendlyTransport;
//  private Transport mockedHiredServiceTransport;
//
//  //@BeforeEach
//  public void beforeTest() {
//    mockedEcoFriendlyTransport = mock(EcoFriendly.class);
//    mockedHiredServiceTransport = mock(HiredService.class);
//
//    dummyStartLocation = new Location(new District(200), "Cool Start Street", "300");
//    dummyEndLocation = new Location(new District(200), "Cool End Street", "700");
//    anotherDummyStartLocation = new Location(new District(200), "Not So Cool Start Street", "100");
//    anotherDummyEndLocation = new Location(new District(200), "Not So Cool End Street", "500");
//    dummyLeg = new Leg(dummyStartLocation, dummyEndLocation, mockedEcoFriendlyTransport);
//    anotherDummyLeg = new Leg(anotherDummyStartLocation, anotherDummyEndLocation, mockedHiredServiceTransport);
//    dummyLegs.add(dummyLeg);
//    dummyLegs.add(anotherDummyLeg);
//    dummyJourney = new Journey(dummyLegs);
//  }
//
//  @Test
//  public void journeyTotalDistance() throws IOException {
//
//    when(mockedEcoFriendlyTransport.getDistance(dummyStartLocation, dummyEndLocation))
//        .thenReturn(new Distance(500, "KM"));
//    when(mockedHiredServiceTransport.getDistance(anotherDummyStartLocation, anotherDummyEndLocation))
//        .thenReturn(new Distance(250, "KM"));
//
//    Distance distance = dummyJourney.getJourneyDistance();
//
//    assertEquals(distance.getValue(), 750);
//  }

}
