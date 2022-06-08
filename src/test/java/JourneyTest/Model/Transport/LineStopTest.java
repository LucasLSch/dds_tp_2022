package JourneyTest.Model.Transport;

import domain.journey.transport.Line;
import domain.journey.transport.PublicTransportType;
import domain.journey.transport.Stop;
import domain.location.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LineStopTest {
  private Line dummyLine;
  private Stop dummyStop;
  private List<Stop> dummyStops = new ArrayList<>();
  private Location coolestDummyLocation;
  private Location dummyLocation;
  private Location anotherDummyLocation;

  @BeforeEach
  public void beforeTest() {
    dummyLocation = new Location(43, "Cool Street", "800");
    anotherDummyLocation = new Location(37, "Not So Cool Street", "1500");
    coolestDummyLocation = new Location(73, "Coolest Street", "400");
    dummyStops.add(new Stop(dummyLocation));
    dummyStops.add(new Stop(anotherDummyLocation));
    dummyLine = new Line(dummyStops, "Cool Line", PublicTransportType.TRAIN);
    dummyStop = new Stop(dummyLine, coolestDummyLocation);
  }
}
