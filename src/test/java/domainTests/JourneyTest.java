package domainTests;

import domain.location.Distance;
import domain.journey.Journey;
import domain.journey.Leg;
import domain.journey.transport.*;
import domain.location.District;
import domain.location.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JourneyTest extends TestDataFill {
  private Journey dummyJourney;
  private List<Leg> dummyLegs = new ArrayList<>();
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
    dummyLeg = new Leg(dummyStartLocation, dummyEndLocation, mockedEcoFriendlyTransport, 0);
    anotherDummyLeg = new Leg(anotherDummyStartLocation,
        anotherDummyEndLocation, mockedHiredServiceTransport, 1);
    dummyLegs.add(dummyLeg);
    dummyLegs.add(anotherDummyLeg);
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

  @Test
  public void journeyDistanceFromLegToAnotherLeg() throws IOException {
    Transport thirdLegTransport = mock(PublicTransport.class);
    Leg thirdLeg = new Leg(new Location(new District(3), "3rdSS", ""),
        new Location(new District(3), "3rdES", ""),
        thirdLegTransport, 2);
    Transport forthLegTransport = mock(PublicTransport.class);
    Leg forthLeg = new Leg(new Location(new District(4), "4thSS", ""),
        new Location(new District(4), "4thES", ""),
        forthLegTransport, 3);

    when(thirdLegTransport.getDistance(any(), any())).thenReturn(new Distance(100, "KM"));
    when(forthLegTransport.getDistance(any(), any())).thenReturn(new Distance(50, "KM"));
    when(mockedHiredServiceTransport.getDistance(any(), any())).thenReturn(new Distance(150, "KM"));

    dummyJourney.addLeg(thirdLeg);
    dummyJourney.addLeg(forthLeg);

    Distance distanceFromLegToLeg = dummyJourney.getDistanceFromTo(anotherDummyLeg, forthLeg);

    assertEquals(distanceFromLegToLeg.getValue(), 300);
  }
}
