package domainTests;

import domain.journey.transport.*;
import domain.location.Location;
import org.junit.jupiter.api.BeforeAll;
import repositories.*;

public abstract class TestDataFill {

  private ActivityDataRepo activityDataRepo = ActivityDataRepo.getInstance();
  private ConsumptionTypeRepo consumptionTypeRepo = ConsumptionTypeRepo.getInstance();
  private EmissionFactorRepo emissionFactorRepo = EmissionFactorRepo.getInstance();
  private JourneyRepo journeyRepo = JourneyRepo.getInstance();
  private LegRepo legRepo = LegRepo.getInstance();
  private LineRepo lineRepo = LineRepo.getInstance();
  private LocationRepo locationRepo = LocationRepo.getInstance();
  private MemberRepo memberRepo = MemberRepo.getInstance();
  private OrganizationRepo organizationRepo = OrganizationRepo.getInstance();
  private SectorRepo sectorRepo = SectorRepo.getInstance();
  private StopRepo stopRepo = StopRepo.getInstance();
  private TransportRepo transportRepo = TransportRepo.getInstance();

  protected TestDataFill() {
    /// TODO
  }

  @BeforeAll
  public void fillRepos() {
    this.createLines();
    this.createTransports();
  }

  private void createTransports() {
    Transport[] transports = {
        new ParticularVehicle(10d, ParticularVehicleType.CAR, Fuel.ELECTRIC),
        new ParticularVehicle(15d, ParticularVehicleType.MOTORBIKE, Fuel.GASOIL),
        new ParticularVehicle(20d, ParticularVehicleType.VAN, Fuel.GNC),
        new HiredService(10d, HiredServiceType.TAXI, "Taxi-123"),
        new HiredService(15d, HiredServiceType.APPLICATION, "NotUber"),
        new EcoFriendly(EcoFriendlyType.WALKING),
        new EcoFriendly(EcoFriendlyType.BICYCLE),
        new EcoFriendly(EcoFriendlyType.SCOOTER)
        // falta transporte publico
    };

    this.transportRepo.saveAll(transports);
  }

  private void createLines() {
/*
    Stop[] stops = {
        new Stop()
    };

    Line nuevaLinea = new Line();
*/
  }


}
