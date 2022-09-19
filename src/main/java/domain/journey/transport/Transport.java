package domain.journey.transport;

import domain.location.Distance;
import domain.location.Location;
import domain.measurements.ConsumptionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.IOException;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "transport_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Transport {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  //Desconozco de donde y como se obtiene el FE de los transportes
  //Por el momento lo dejo como un atributo del transporte
  protected Double fuelConsumptionPerKm;

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
    return null;
  }

}
