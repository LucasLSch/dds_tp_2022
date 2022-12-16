package domainTests;

import ddsutn.domain.journey.transport.Line;
import ddsutn.domain.journey.transport.PublicTransportType;
import ddsutn.domain.journey.transport.Stop;
import ddsutn.domain.location.Distance;
import ddsutn.domain.location.District;
import ddsutn.domain.location.Location;
import ddsutn.domain.measurements.unit.Proportionality;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LineStopTest extends TestDataFill {

  private Line dummyLine;
  private Stop firstDummyStop;
  private Stop secondDummyStop;
  private Stop thirdDummyStop;
  private Stop forthDummyStop;
  private final List<Stop> dummyStops = new ArrayList<>();
  private Location coolestDummyLocation;
  private Location dummyLocation;
  private Location anotherDummyLocation;

  @BeforeEach
  public void beforeTest() {
    dummyLocation = new Location(new District(43), "Cool Street", "800");
    anotherDummyLocation = new Location(new District(37), "Not So Cool Street", "1500");
    coolestDummyLocation = new Location(new District(73), "Coolest Street", "400");
    firstDummyStop = new Stop(dummyLocation, new Distance(100d, kilometer(Proportionality.DIRECT)));
    secondDummyStop = new Stop(anotherDummyLocation, new Distance(50d, kilometer(Proportionality.DIRECT)));
    thirdDummyStop = new Stop(coolestDummyLocation, new Distance(15d, kilometer(Proportionality.DIRECT)));
    forthDummyStop = new Stop(coolestDummyLocation, new Distance(0d, kilometer(Proportionality.DIRECT)));
    dummyStops.add(firstDummyStop);
    dummyStops.add(secondDummyStop);
    dummyStops.add(thirdDummyStop);
    dummyStops.add(forthDummyStop);
    dummyLine = new Line(dummyStops, "Cool Line", PublicTransportType.TRAIN);
  }

  @Test
  public void distanceBetweenStops() {
    Distance distance = dummyLine.getDistanceBetween(secondDummyStop, forthDummyStop);
    assertEquals(distance.getValue(), 65);
  }
}
