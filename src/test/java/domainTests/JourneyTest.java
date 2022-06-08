package domainTests;

import domain.journey.Journey;
import domain.journey.Leg;
import domain.journey.transport.EcoFriendly;
import domain.journey.transport.HiredService;
import domain.journey.transport.HiredServiceType;
import domain.location.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JourneyTest {
  private Journey dummyJourney;
  private List<Leg> dummyLegs = new ArrayList<>();
  private Location dummyStartLocation;
  private Location dummyEndLocation;
  private Location anotherDummyStartLocation;
  private Location anotherDummyEndLocation;

  @BeforeEach
  public void beforeTest() {
    dummyStartLocation = new Location(200, "Cool Start Street", "300");
    dummyEndLocation = new Location(200, "Cool End Street", "700");
    anotherDummyStartLocation = new Location(200, "Not So Cool Start Street", "100");
    anotherDummyEndLocation = new Location(200, "Not So Cool End Street", "500");
    dummyLegs.add(new Leg(dummyStartLocation, dummyEndLocation, new EcoFriendly()));
    dummyLegs.add(new Leg(anotherDummyStartLocation, anotherDummyEndLocation,
        new HiredService( HiredServiceType.APPLICATION, "Cabbagefy")));
    dummyJourney = new Journey(dummyLegs);
  }
}
