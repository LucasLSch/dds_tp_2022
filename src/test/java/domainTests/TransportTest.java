package domainTests;


import ddsutn.domain.journey.transport.*;
import ddsutn.domain.location.Distance;
import ddsutn.domain.location.Location;
import ddsutn.domain.measurements.ConsumptionType;
import ddsutn.domain.measurements.unit.Proportionality;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TransportTest extends TestDataFill {
  private Transport dummyPublic;
  private Transport dummyHiredService;
  private Transport dummyParticularVehicle;
  private Transport dummyEco;
  private Line dummyLine;
  private final List<Stop> dummyStops = new ArrayList<>();
  private Stop dummyStartStop;
  private Stop dummyEndStop;
  private Location mockedLocation;
  private Location anotherMockedLocation;

  @BeforeEach
  public void beforeTest() {
    mockedLocation = mock(Location.class);
    anotherMockedLocation = mock(Location.class);
    dummyStartStop = new Stop(mockedLocation, new Distance(10d, kilometer(Proportionality.DIRECT)));
    dummyEndStop = new Stop(anotherMockedLocation, new Distance(0d, kilometer(Proportionality.DIRECT)));
    dummyStops.add(dummyStartStop);
    dummyStops.add(dummyEndStop);
    dummyLine = new Line(dummyStops, "Cool line", PublicTransportType.BUS);
    dummyPublic = new PublicTransport(dummyLine, dummyStartStop, dummyEndStop);
    dummyHiredService = new HiredService(HiredServiceType.TAXI, "Radio Taxi");
    dummyParticularVehicle = new ParticularVehicle(ParticularVehicleType.VAN, Fuel.OIL);
    dummyEco = new EcoFriendly(EcoFriendlyType.BICYCLE);
  }

  @Test
  public void publicTransportDistance() throws IOException {
    Distance distance = dummyPublic.getDistance(mockedLocation, anotherMockedLocation);
    assertEquals(distance.getValue(), 10);
  }

  @Test
  public void hiredServiceTransportDistance() throws IOException {
    when(mockedLocation.getDistanceTo(anotherMockedLocation))
        .thenReturn(new Distance(20d, kilometer(Proportionality.DIRECT)));

    Distance distance = dummyHiredService.getDistance(mockedLocation, anotherMockedLocation);
    assertEquals(distance.getValue(), 20);
  }

  @Test
  public void transportCanOrCannotBeShared() {
    assertTrue(dummyHiredService.isShareable());
    assertFalse(dummyPublic.isShareable());
    assertFalse(dummyEco.isShareable());
    assertTrue(dummyParticularVehicle.isShareable());
  }

  @Test
  public void transportsCanGetItsConsumption() throws IOException {
    when(mockedLocation.getDistanceTo(anotherMockedLocation))
        .thenReturn(new Distance(10d, kilometer(Proportionality.DIRECT)));

    Double ptConsumption = dummyPublic.getConsumption(mockedLocation, anotherMockedLocation);
    Double hsConsumption = dummyHiredService.getConsumption(mockedLocation, anotherMockedLocation);
    Double efConsumption = dummyEco.getConsumption(mockedLocation, anotherMockedLocation);
    Double pvConsumption = dummyParticularVehicle.getConsumption(mockedLocation, anotherMockedLocation);

    assertEquals(ptConsumption, 50d);
    assertEquals(hsConsumption, 100d);
    assertEquals(efConsumption, 0d);
    assertEquals(pvConsumption, 150d);
  }

  @Test
  public void transportCanGetItsConsumptionType() {
    ConsumptionType ptConsumption = dummyPublic.getConsumptionType();
    ConsumptionType hsConsumption = dummyHiredService.getConsumptionType();
    ConsumptionType efConsumption = dummyEco.getConsumptionType();
    ConsumptionType pvConsumption = dummyParticularVehicle.getConsumptionType();

    assertNotNull(ptConsumption);
    assertNotNull(hsConsumption);
    assertNotNull(efConsumption);
    assertNotNull(pvConsumption);
  }
}
