package domainTests;

import domain.journey.transport.Line;
import domain.journey.transport.PublicTransportType;
import domain.journey.transport.Stop;
import domain.location.Distance;
import domain.location.District;
import domain.location.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LineStopTest extends TestDataFill {

//  private Line dummyLine;
//  private Stop firstDummyStop;
//  private Stop secondDummyStop;
//  private Stop thirdDummyStop;
//  private Stop forthDummyStop;
//  private List<Stop> dummyStops = new ArrayList<>();
//  private Location coolestDummyLocation;
//  private Location dummyLocation;
//  private Location anotherDummyLocation;
//
//  @BeforeEach
//  public void beforeTest() {
//    dummyLocation = new Location(new District(43), "Cool Street", "800");
//    anotherDummyLocation = new Location(new District(37), "Not So Cool Street", "1500");
//    coolestDummyLocation = new Location(new District(73), "Coolest Street", "400");
//    firstDummyStop = new Stop(dummyLine, dummyLocation, new Distance(100, "KM"));
//    secondDummyStop = new Stop(dummyLine, anotherDummyLocation, new Distance(50, "KM"));
//    thirdDummyStop = new Stop(dummyLine, coolestDummyLocation, new Distance(15, "KM"));
//    forthDummyStop = new Stop(dummyLine,coolestDummyLocation, new Distance(0, "KM"));
//    dummyStops.add(firstDummyStop);
//    dummyStops.add(secondDummyStop);
//    dummyStops.add(thirdDummyStop);
//    dummyStops.add(forthDummyStop);
//    dummyLine = new Line(dummyStops, "Cool Line", PublicTransportType.TRAIN);
//  }
//
//  @Test
//  public void distanceBetweenStops() {
//    Distance distance = dummyLine.getDistanceBetween(secondDummyStop, forthDummyStop);
//    assertEquals(distance.getValue(), 65);
//  }
}
