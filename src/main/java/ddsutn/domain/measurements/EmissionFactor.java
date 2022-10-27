package ddsutn.domain.measurements;

import ddsutn.domain.measurements.unit.Unit;
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

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "emission_factor_id")
  private Set<Unit> units;

  public EmissionFactor(Double value, Set<Unit> units) {
    this.value = value;
    this.units = units;
  }

}
