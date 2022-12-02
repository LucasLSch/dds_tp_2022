package ddsutn.domain.journey.transport;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@DiscriminatorValue(value = "ECO_FRIENDLY")
public class EcoFriendly extends Transport {

  @Column(name = "ef_type")
  @Enumerated(value = EnumType.STRING)
  private EcoFriendlyType type;

  public EcoFriendly(EcoFriendlyType type) {
    super(0d);
    this.type = type;
  }

  @Override
  public Double getFuelConsumptionPerKm() {
    return this.fuelConsumptionPerKm;
  }

  @Override
  public Boolean isShareable() {
    return false;
  }

  @Override
  public String print() {
    return this.type.name();
  }
}
