package domain.journey.transport;

import domain.location.Distance;
import domain.location.Location;
import domain.measurements.ConsumptionType;
import java.io.IOException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Transport {

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
