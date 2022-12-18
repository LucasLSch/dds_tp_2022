package ddsutn.domain.measurements;

import ddsutn.domain.measurements.unit.Unit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
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

  @OneToMany(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "activity_data")
  private Set<CarbonFootprint> carbonFootprints;

  public ActivityData(
      @NotNull ConsumptionType consumptionType,
      Double consumptionValue,
      PeriodicityFormat periodicityFormat,
      String periodicity
  ) {
    this.consumptionType = consumptionType;
    this.consumptionValue = consumptionValue;
    this.periodicityFormat = periodicityFormat;
    this.periodicity = periodicity;
    this.carbonFootprints = new HashSet<>();
  }

  public LocalDate getPeriodicityDate() {
    return this.periodicityFormat.getDate(this.periodicity);
  }

  public CarbonFootprint getCarbonFootprint(Set<Unit> objectiveUnits) {
    Double value = this.consumptionType.calculateFor(this.consumptionValue);
    Set<Unit> units = CarbonFootprint.getDefaultUnit();
    CarbonFootprint finalCF = new CarbonFootprint(value, units);

    this.registerCarbonFootprint(finalCF);
    return finalCF.getOn(objectiveUnits);
  }

  public CarbonFootprint getCarbonFootprint() {
    return this.getCarbonFootprint(CarbonFootprint.getDefaultUnit());
  }

  private void registerCarbonFootprint(CarbonFootprint someCarbonFootprint) {
    someCarbonFootprint.setDate(LocalDate.now());
    this.carbonFootprints.add(someCarbonFootprint);
  }
}
