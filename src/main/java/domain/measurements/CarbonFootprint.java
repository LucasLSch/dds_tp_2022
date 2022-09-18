package domain.measurements;

import domain.journey.Journey;
import domain.measurements.unit.UnitExpression;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Stream;

import domain.organization.Member;
import domain.organization.Organization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

  @Embedded
  private UnitExpression unitExpression;

  @Column(name = "date")
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

  public CarbonFootprint(Double value, UnitExpression unitExpression, LocalDate date) {
    this.value = value;
    this.unitExpression = unitExpression;
    this.date = date;
  }

  public void multiplyValue(Double someFactor) {
    this.value *= someFactor;
  }

  public CarbonFootprint getOn(UnitExpression someUE) {
    if (this.unitExpression.isConvertibleTo(someUE)) {
      this.multiplyValue(Math.pow(10f, this.unitExpression.getExpForConvertionTo(someUE)));
      this.unitExpression = someUE;
    }
    return this;
  }

  public static CarbonFootprint sum(UnitExpression someUE, CarbonFootprint... carbonFootprints) {
    Stream<CarbonFootprint> cfStream = Arrays.stream(carbonFootprints).sequential();
    cfStream = cfStream.map(cf -> cf.getOn(someUE));
    Double totalValue = cfStream.mapToDouble(CarbonFootprint::getValue).sum();

    return new CarbonFootprint(totalValue, someUE, LocalDate.now());
  }


}
