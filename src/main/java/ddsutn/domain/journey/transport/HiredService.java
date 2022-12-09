package ddsutn.domain.journey.transport;

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

  public HiredService(HiredServiceType hsType, String serviceName) {
    super(10);
    this.hsType = hsType;
    this.name = serviceName;
  }

  @Override
  public Boolean isShareable() {
    return true;
  }

  @Override
  public String print() {
    return this.name + " (" + this.hsType.name() + ")";
  }
}
