package domain.journey.transport;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@DiscriminatorValue(value = "HIRED_SERVICE")
public class HiredService extends Transport {

  @Column(name = "hs_type")
  @Enumerated(value = EnumType.STRING)
  private HiredServiceType hsType;

  @Column(name = "hs_name")
  private String name;

  public HiredService(Double fuelConsumptionPerKm, HiredServiceType hsType, String serviceName) {
    super(fuelConsumptionPerKm);
    this.hsType = hsType;
    this.name = serviceName;
  }

  @Override
  public Boolean isShareable() {
    return true;
  }
}
