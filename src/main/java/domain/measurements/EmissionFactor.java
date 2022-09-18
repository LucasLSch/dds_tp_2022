package domain.measurements;

import domain.exceptions.InvalidUnitException;
import domain.measurements.unit.UnitExpression;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "emission_factor")
public class EmissionFactor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "value")
  private Double value;

  @Embedded
  private UnitExpression unit;

  @OneToOne(mappedBy = "emissionFactor")
  private ConsumptionType consumptionTypeAsociated;

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
