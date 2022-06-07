package JourneyTest.Model;

import domain.journey.Journey;
import domain.journey.Leg;
import domain.journey.transport.EcoFriendly;
import domain.journey.transport.HiredService;
import domain.journey.transport.HiredServiceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JourneyTest {
  private Journey dummyJourney;
  private List<Leg> dummyLegs = new ArrayList<>();

  @BeforeEach
  public void beforeTest() {
    dummyLegs.add(new Leg("Start", "End", new EcoFriendly()));
    dummyLegs.add(new Leg("Start", "End", new HiredService( HiredServiceType.APPLICATION,
                                                                          "Cabbagefy")));
    dummyJourney = new Journey(dummyLegs);
  }
}
