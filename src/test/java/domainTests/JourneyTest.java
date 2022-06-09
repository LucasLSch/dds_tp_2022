package domainTests;

import domain.journey.Journey;
import domain.journey.Leg;
import domain.journey.transport.*;
import domain.location.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.georef.Distance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JourneyTest {
  private Journey dummyJourney;
  private List<Leg> dummyLegs = new ArrayList<>();
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

    dummyStartLocation = new Location(200, "Cool Start Street", "300");
    dummyEndLocation = new Location(200, "Cool End Street", "700");
    anotherDummyStartLocation = new Location(200, "Not So Cool Start Street", "100");
    anotherDummyEndLocation = new Location(200, "Not So Cool End Street", "500");
    dummyLegs.add(new Leg(dummyStartLocation, dummyEndLocation, mockedEcoFriendlyTransport));
    dummyLegs.add(new Leg(anotherDummyStartLocation, anotherDummyEndLocation, mockedHiredServiceTransport));
    dummyJourney = new Journey(dummyLegs);
  }

  @Test
  public void journeyTotalDistance() throws IOException {

    when(mockedEcoFriendlyTransport.getDistance(dummyStartLocation, dummyEndLocation))
        .thenReturn(new Distance(500, "KM"));
    when(mockedHiredServiceTransport.getDistance(anotherDummyStartLocation, anotherDummyEndLocation))
        .thenReturn(new Distance(250, "KM"));

    Distance distance = dummyJourney.getJourneyDistance();

    assertEquals(distance.getValue(), 750);
  }


}
