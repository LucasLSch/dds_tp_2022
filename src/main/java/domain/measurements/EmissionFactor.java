package domain.measurements;

import domain.exceptions.InvalidUnitException;
import domain.measurements.unit.Unit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "emission_factor")
public class EmissionFactor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "value")
  private Double value;

  @OneToMany(mappedBy = "emissionFactor", cascade = CascadeType.ALL)
  private Set<Unit> units;

  @OneToOne(mappedBy = "emissionFactor")
  private ConsumptionType consumptionTypeAsociated;

  public EmissionFactor(Double value, Set<Unit> units, ConsumptionType consumptionTypeAsociated) {
    this.value = value;
    this.units = units;
    this.consumptionTypeAsociated = consumptionTypeAsociated;
  }

  // Pensar si es acorde que se auto setee en el tipo de consumo en el momento que se crea el FE.
  private void linkTo(ConsumptionType consumptionType) {
    if (true /* TODO condicion*/) {
      this.consumptionTypeAsociated = consumptionType;
      consumptionType.setEmissionFactor(this);
    } else {
      throw new InvalidUnitException();
    }
  }

}
