package domain.measurements.unit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "simple_unit")
public class SimpleUnit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "base_unit")
  private BaseUnit baseUnit;

  @Column(name = "exponent")
  private Integer exponent;

  @Enumerated(value = EnumType.STRING)
  private Proportionality proportionality;

  @Embedded
  @AssociationOverrides(value = {
          @AssociationOverride(name = "emissionFactor", joinColumns = @JoinColumn(name = "emission_factor_id")),
          @AssociationOverride(name = "carbonFootprint", joinColumns = @JoinColumn(name = "carbon_footprint_id")),
          @AssociationOverride(name = "consumptionType", joinColumns = @JoinColumn(name = "consumption_type_id"))
  })
  private UnitExpression unitExpression;

  public Integer getProportionalityFactor() {
    return this.proportionality.getFactor();
  }

  public Integer getSignedExponent() {
    return this.getExponent() * this.getProportionalityFactor();
  }

  public Boolean isDirectlyProportional() {
    return this.proportionality.equals(Proportionality.DIRECT);
  }

}
