package domain.journey.transport;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ParticularVehicle extends Transport {

  @Getter
  private ParticularVehicleType type;
  @Getter
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
