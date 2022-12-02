package ddsutn.domain.journey.transport;

import ddsutn.domain.location.Distance;
import ddsutn.domain.location.Location;
import ddsutn.domain.measurements.CarbonFootprint;
import ddsutn.domain.measurements.ConsumptionType;
import ddsutn.domain.measurements.EmissionFactor;
import ddsutn.domain.measurements.unit.BaseUnit;
import ddsutn.domain.measurements.unit.Proportionality;
import ddsutn.domain.measurements.unit.Unit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "transport_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Transport {

  //Desconozco de donde y como se obtiene el FE de los transportes
  //Por el momento lo dejo como un atributo del transporte
  protected Double fuelConsumptionPerKm;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  public Transport(double fuelConsumptionPerKm) {
    this.fuelConsumptionPerKm = fuelConsumptionPerKm;
  }

  public Distance getDistance(Location start, Location end) throws IOException {
    return start.getDistanceTo(end);
  }

  public Double getConsumption(Location start, Location end) throws IOException {
    return this.getFuelConsumptionPerKm() * this.getDistance(start, end).getValue();
  }

  public abstract Boolean isShareable();

  public ConsumptionType getConsumptionType() {
    Set<Unit> unitSet = new HashSet<>(Arrays.asList(
        new Unit(BaseUnit.LITER, 1, Proportionality.DIRECT),
        new Unit(BaseUnit.METER, 3, Proportionality.INVERSE)
    ));
    return new ConsumptionType(
        "Transporte",
        unitSet,
        "Transporte",
        "Personal",
        new EmissionFactor(1d, CarbonFootprint.getDefaultUnit())
    );
  }

  public abstract String print();

}
