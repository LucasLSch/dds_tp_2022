package domain.journey.transport;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ParticularVehicle extends Transport {

  private ParticularVehicleType type;
  private Fuel fuel;

  @Override
  public Boolean isShareable() {
    return true;
  }

}
