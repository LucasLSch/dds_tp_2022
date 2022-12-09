package ddsutn.domain.journey.transport;

import lombok.NoArgsConstructor;

import javax.persistence.*;

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

  public ParticularVehicle(ParticularVehicleType type, Fuel fuel) {
    super(15);
    this.type = type;
    this.fuel = fuel;
  }

  @Override
  public Boolean isShareable() {
    return true;
  }

  @Override
  public String print() {
    return this.type.name();
  }

}
