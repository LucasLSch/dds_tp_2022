package domain.journey.transport;

import lombok.Getter;

public class ParticularVehicle extends Transport {

  @Getter
  private ParticularVehicleType type;
  @Getter
  private Fuel fuel;

  public ParticularVehicle(Double fuelConsumptionPerKm, ParticularVehicleType type, Fuel fuel) {
    super(fuelConsumptionPerKm);
    this.type = type;
    this.fuel = fuel;
  }

  @Override
  public Boolean isShareable() {
    return true;
  }

}
