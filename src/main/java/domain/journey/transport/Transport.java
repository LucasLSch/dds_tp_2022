package domain.journey.transport;

import domain.location.Distance;
import domain.location.Location;
import domain.measurements.ConsumptionType;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

public abstract class Transport {

  //Desconozco de donde y como se obtiene el FE de los transportes
  //Por el momento lo dejo como un atributo del transporte
  @Getter
  @Setter
  protected ConsumptionType consumptionType;

  public Distance getDistance(Location start, Location end) throws IOException {
    return start.getDistanceTo(end);
  }

  public Integer getConsumption(Location start, Location end) throws IOException {
    return this.getConsumptionPerKm() * this.getDistance(start, end).getValue();
  }

  public abstract Integer getConsumptionPerKm();

  public abstract Boolean isShareable();

}
