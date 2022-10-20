package ddsutn.domain.journey.transport;

import javax.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@DiscriminatorValue(value = "PARTICULAR_VEHICLE")
public class ParticularVehicle extends Transport {

  @Column(name = "pv_type")
  @Enumerated(value = EnumType.STRING)
  private ParticularVehicleType type;

  @Column(name = "pv_fuel_type")
  @Enumerated(value = EnumType.STRING)
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
