package domain.measurements;

import domain.measurements.unit.UnitExpression;
import java.time.LocalDate;

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
@Table(name = "activity_data")
public class ActivityData {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "consumption_type_id")
  private ConsumptionType consumptionType;

  @Column(name = "consumption_value")
  private Double consumptionValue;

  @Enumerated(value = EnumType.STRING)
  private PeriodicityFormat periodicityFormat;

  @Column(name = "periodicity")
  private String periodicity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "organization_id")
  private Organization organization;

  public ActivityData(ConsumptionType consumptionType,
                      Double consumptionValue,
                      PeriodicityFormat periodicityFormat,
                      String periodicity,
                      Organization organization) {
    this.consumptionType = consumptionType;
    this.consumptionValue = consumptionValue;
    this.periodicityFormat = periodicityFormat;
    this.periodicity = periodicity;
    this.organization = organization;
  }

  public LocalDate getPeriodicityDate() {
    return this.periodicityFormat.getDate(this.periodicity);
  }

  public CarbonFootprint getCarbonFootprint(UnitExpression someUnitExpression) {
    Double value = this.getConsumptionValue() * this.getConsumptionType().getEmissionFactorValue();
    UnitExpression unitExpression = this.consumptionType.getUnitExpression();
    return new CarbonFootprint(value, unitExpression, null).getOn(someUnitExpression);
  }
}
