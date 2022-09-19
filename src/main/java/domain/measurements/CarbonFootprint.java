package domain.measurements;

import domain.journey.Journey;
import domain.measurements.unit.Unit;
import domain.measurements.unit.UnitExpression;
import domain.organization.Member;
import domain.organization.Organization;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.uqbarproject.jpa.java8.extras.convert.LocalDateConverter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Arrays;
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

  @OneToMany(mappedBy = "carbonFootprint", cascade = CascadeType.ALL)
  private Set<Unit> units;

  @Column(name = "date")
  @Convert(converter = LocalDateConverter.class)
  private LocalDate date;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "organization_id")
  private Organization organization;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "journey_id")
  private Journey journey;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "activity_data_id")
  private ActivityData activityData;

  public CarbonFootprint(Double value, Set<Unit> units, LocalDate date) {
    this.value = value;
    this.setUnits(units);
    this.date = date;
  }

  public void multiplyValue(Double someFactor) {
    this.value *= someFactor;
  }

  public CarbonFootprint getOn(Set<Unit> objectiveUnits) {
    if (UnitExpression.isConvertibleTo(this.units, objectiveUnits)) {
      this.multiplyValue(Math.pow(10f, UnitExpression.getExpForConvertionTo(this.units, objectiveUnits)));
      this.units = objectiveUnits;
    }
    return this;
  }

  public void setUnits(Set<Unit> units) {
    this.units = units;
    units.forEach(unit -> unit.setCarbonFootprint(this));
  }

  public static CarbonFootprint sum(Set<Unit> objectiveUnits, CarbonFootprint... carbonFootprints) {
    Stream<CarbonFootprint> cfStream = Arrays.stream(carbonFootprints).sequential();
    cfStream = cfStream.map(cf -> cf.getOn(objectiveUnits));
    Double totalValue = cfStream.mapToDouble(CarbonFootprint::getValue).sum();

    return new CarbonFootprint(totalValue, objectiveUnits, LocalDate.now());
  }


}
