package domain.measurements;

import domain.measurements.unit.Unit;
import domain.organization.Organization;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

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

  public ActivityData(
          ConsumptionType consumptionType,
          Double consumptionValue,
          PeriodicityFormat periodicityFormat,
          String periodicity,
          Organization organization
  ) {
    this.consumptionType = consumptionType;
    this.consumptionValue = consumptionValue;
    this.periodicityFormat = periodicityFormat;
    this.periodicity = periodicity;
    this.organization = organization;
  }

  public LocalDate getPeriodicityDate() {
    return this.periodicityFormat.getDate(this.periodicity);
  }

  public CarbonFootprint getCarbonFootprint(Set<Unit> objectiveUnits) {
    Double value = this.getConsumptionValue() * this.getConsumptionType().getEmissionFactorValue();
    Set<Unit> units = this.consumptionType.getUnits();
    return new CarbonFootprint(value, units, LocalDate.now()).getOn(objectiveUnits);
  }
}
