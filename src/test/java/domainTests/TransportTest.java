package domainTests;

/*
public class TransportTest extends TestDataFill {
  private Transport dummyPublic;
  private Transport dummyHiredService;
  private Line dummyLine;
  private List<Stop> dummyStops = new ArrayList<>();
  private Stop dummyStartStop;
  private Stop dummyEndStop;
  private Location mockedLocation;
  private Location anotherMockedLocation;

  @BeforeEach
  public void beforeTest() {
    mockedLocation = mock(Location.class);
    anotherMockedLocation = mock(Location.class);
    dummyStartStop = new Stop(mockedLocation, new Distance(10, "KM"), 0);
    dummyEndStop = new Stop(anotherMockedLocation, new Distance(0, "KM"), 1);
    dummyStops.add(dummyStartStop);
    dummyStops.add(dummyEndStop);
    dummyLine = new Line(dummyStops, "Cool line", PublicTransportType.BUS);
    dummyPublic = new PublicTransport(dummyLine, dummyStartStop, dummyEndStop);
    dummyHiredService = new HiredService(HiredServiceType.TAXI, "Radio Taxi");
  }

  @Test
  public void publicTransportDistance() throws IOException {
    Distance distance = dummyPublic.getDistance(mockedLocation, anotherMockedLocation);
    assertEquals(distance.getValue(), 10);
  }

  @Test
  public void hiredServiceTransportDistance() throws IOException {
    when(mockedLocation.getDistanceTo(anotherMockedLocation)).thenReturn(new Distance(20, "KM"));

    Distance distance = dummyHiredService.getDistance(mockedLocation, anotherMockedLocation);
    assertEquals(distance.getValue(), 20);
  }
}
*/