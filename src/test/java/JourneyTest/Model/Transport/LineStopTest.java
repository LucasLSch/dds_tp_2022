package JourneyTest.Model.Transport;

import domain.journey.transport.Line;
import domain.journey.transport.PublicTransportType;
import domain.journey.transport.Stop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LineStopTest {
  private Line dummyLine;
  private Stop dummyStop;
  private List<Stop> dummyStops = new ArrayList<>();

  @BeforeEach
  public void beforeTest() {
    dummyStops.add(new Stop("Cool Place"));
    dummyStops.add(new Stop("Another Cool Place"));
    dummyLine = new Line(dummyStops, "Cool Line", PublicTransportType.TRAIN);
    dummyStop = new Stop(dummyLine, "Coolest Place");
  }

  @Test
  public void lineCreationIsCorrect() {
    assertNotNull(dummyLine);
  }

  @Test
  public void stopCreationIsCorrect() {
    assertNotNull(dummyStop);
  }
}
