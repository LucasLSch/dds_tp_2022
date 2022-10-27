package ddsutn.domain.measurements;

import ddsutn.domain.measurements.unit.Unit;
import ddsutn.domain.measurements.unit.UnitExpression;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "consumption_type")
public class ConsumptionType {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "consumption_type_id")
  private Set<Unit> units;

  @Column(name = "activity")
  private String activity;

  @Column(name = "scope")
  private String scope;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "emission_factor_id")
  private EmissionFactor emissionFactor;

  public ConsumptionType(String name,
                         Set<Unit> units,
                         String activity,
                         String scope,
                         EmissionFactor emissionFactor) {
    this.name = name;
    this.units = units;
    this.activity = activity;
    this.scope = scope;
    this.emissionFactor = emissionFactor;
  }

  public void setEmissionFactor(EmissionFactor someEF) {
    this.emissionFactor = someEF;
  }

  public Double getEmissionFactorValue() {
    return this.getEmissionFactor().getValue();
  }

  public String print() {
    return this.getName() + " (" + UnitExpression.printUnits(this.getUnits()) + ")";
  }

  public Double calculateFor(Double someValue) {
    return this.getEmissionFactorValue() * someValue;
  }

}
