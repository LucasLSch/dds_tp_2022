package ddsutn.domain.measurements;

import ddsutn.domain.measurements.unit.Unit;
import java.util.Set;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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
