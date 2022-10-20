package ddsutn.domain.journey.transport;

import javax.persistence.*;
import lombok.NoArgsConstructor;

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
}
