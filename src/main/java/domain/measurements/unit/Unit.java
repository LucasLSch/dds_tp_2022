package domain.measurements.unit;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "unit")
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

  public String print() {
    return this.baseUnit.toString() + "exp" + this.exponent.toString();
  }
}
