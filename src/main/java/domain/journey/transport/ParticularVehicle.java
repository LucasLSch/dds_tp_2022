package domain.journey.transport;

public class ParticularVehicle implements Transport {

  private ParticularVehicleType type;
  private Fuel fuel;


  @Override
  public Boolean isShareable() {
    return true;
  }
}
