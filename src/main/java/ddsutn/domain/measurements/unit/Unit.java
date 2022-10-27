package ddsutn.domain.measurements.unit;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    return this.getExpName() + this.baseUnit.toString().toLowerCase();
  }

  private String getExpName() {

    String expName = "";

    switch (this.exponent) {
      case -9:
        expName = "nano";
        break;
      case -6:
        expName = "micro";
        break;
      case -3:
        expName = "mili";
        break;
      case -2:
        expName = "centi";
        break;
      case -1:
        expName = "deci";
        break;
      case 0:
        expName = "";
        break;
      case 1:
        expName = "deca";
        break;
      case 2:
        expName = "hecto";
        break;
      case 3:
        expName = "kilo";
        break;
      case 6:
        expName = "mega";
        break;
      case 9:
        expName = "giga";
        break;
      case 12:
        expName = "tera";
        break;
      default:
        expName = this.exponent + " exp of ";
        break;
    }

    return expName;
  }


}
