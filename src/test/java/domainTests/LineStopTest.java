package domainTests;

import domain.journey.transport.Line;
import domain.journey.transport.PublicTransportType;
import domain.journey.transport.Stop;
import domain.location.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.georef.Distance;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LineStopTest {
  private Line dummyLine;
  private Stop firstDummyStop;
  private Stop secondDummyStop;
  private Stop thirdDummyStop;
  private Stop forthDummyStop;
  private List<Stop> dummyStops = new ArrayList<>();
  private Location coolestDummyLocation;
  private Location dummyLocation;
  private Location anotherDummyLocation;

  @BeforeEach
  public void beforeTest() {
    dummyLocation = new Location(43, "Cool Street", "800");
    anotherDummyLocation = new Location(37, "Not So Cool Street", "1500");
    coolestDummyLocation = new Location(73, "Coolest Street", "400");
    firstDummyStop = new Stop(dummyLocation, new Distance(100, "KM"), 0);
    secondDummyStop = new Stop(anotherDummyLocation, new Distance(50, "KM"), 1);
    thirdDummyStop = new Stop(coolestDummyLocation, new Distance(15, "KM"), 2);
    forthDummyStop = new Stop(coolestDummyLocation, new Distance(0, "KM"), 3);
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
