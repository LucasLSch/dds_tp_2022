package domain.measurements.unit;

import domain.measurements.CarbonFootprint;
import domain.measurements.ConsumptionType;
import domain.measurements.EmissionFactor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "simple_unit")
public class Unit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "base_unit")
  @Enumerated(value = EnumType.STRING)
  private BaseUnit baseUnit;

  @Column(name = "exponent")
  private Integer exponent;

  @Enumerated(value = EnumType.STRING)
  private Proportionality proportionality;

  @ManyToOne
  @JoinColumn(name = "carbon_footprint_id")
  private CarbonFootprint carbonFootprint;

  @ManyToOne
  @JoinColumn(name = "consumption_type_id")
  private ConsumptionType consumptionType;

  @ManyToOne
  @JoinColumn(name = "emission_factor_id")
  private EmissionFactor emissionFactor;

  public Unit(
          BaseUnit baseUnit,
          Integer exponent,
          Proportionality proportionality
  ) {
    this.baseUnit = baseUnit;
    this.exponent = exponent;
    this.proportionality = proportionality;
  }

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
