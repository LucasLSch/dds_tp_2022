package ddsutn.domain.measurements;

import ddsutn.domain.measurements.unit.BaseUnit;
import ddsutn.domain.measurements.unit.Proportionality;
import ddsutn.domain.measurements.unit.Unit;
import ddsutn.domain.measurements.unit.UnitExpression;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.uqbarproject.jpa.java8.extras.convert.LocalDateConverter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "carbon_footprint")
public class CarbonFootprint {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "value")
  private Double value;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "carbon_footprint_id")
  private Set<Unit> units;

  @Column(name = "date")
  @Convert(converter = LocalDateConverter.class)
  private LocalDate date;


  public CarbonFootprint(Double value, Set<Unit> units) {
    this.value = value;
    this.setUnits(units);
  }

  public static CarbonFootprint sum(Set<Unit> objectiveUnits, CarbonFootprint... carbonFootprints) {
    Stream<CarbonFootprint> cfStream = Arrays.stream(carbonFootprints).sequential();
    cfStream = cfStream.map(cf -> cf.getOn(objectiveUnits));
    Double totalValue = cfStream.mapToDouble(CarbonFootprint::getValue).sum();

    return new CarbonFootprint(totalValue, objectiveUnits);
  }

  public static CarbonFootprint sum(CarbonFootprint... carbonFootprints) {
    return sum(getDefaultUnit(), carbonFootprints);
  }

  public static Set<Unit> getDefaultUnit() {
    return new HashSet<>(Collections.singletonList(new Unit(BaseUnit.GRAM, 3, Proportionality.DIRECT)));
  }

  public CarbonFootprint multiplyValue(Double someFactor) {
    this.value *= someFactor;
    return this;
  }

  public CarbonFootprint getOn(Set<Unit> objectiveUnits) {
    if (UnitExpression.isConvertibleTo(this.units, objectiveUnits)) {
      this.multiplyValue(Math.pow(10f,
              UnitExpression.getExpForConvertionTo(this.units, objectiveUnits)
          )
      );
      this.units = objectiveUnits;
    }
    return this;
  }

  public void setUnits(Set<Unit> units) {
    this.units = units;
  }

}
