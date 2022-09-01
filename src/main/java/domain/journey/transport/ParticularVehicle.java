package domain.journey.transport;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ParticularVehicle extends Transport {

  private ParticularVehicleType type;
  private Fuel fuel;
  private Integer consumption;

  @Override
  public Integer getConsumptionPerKm() {
    return consumption;
  }

  @Override
  public Boolean isShareable() {
    return true;
  }

}
