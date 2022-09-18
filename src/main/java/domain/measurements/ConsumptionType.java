package domain.measurements;

import domain.measurements.unit.UnitExpression;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "consumption_type")
public class ConsumptionType {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Embedded
  private UnitExpression unitExpression;

  @Column(name = "activity")
  private String activity;

  @Column(name = "scope")
  private String scope;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "emission_factor_id")
  private EmissionFactor emissionFactor;

  public UnitExpression getUnitExpression() {
    return this.unitExpression;
  }

  public void setEmissionFactor(EmissionFactor someEF) {
    this.emissionFactor = someEF;
  }

  public Double getEmissionFactorValue() {
    return this.getEmissionFactor().getValue();
  }
}
