package domain.journey.transport;

import domain.location.Location;

public class PublicTransport implements Transport {

  private Line line;
  private Stop startStop;
  private Stop endStop;

  @Override
  public Integer getDistance(Location start, Location end) {
    /*
     * Se complica mucho realizar esto sin la existencia de listas ordenadas y
     * el diesnio actual tampoco ayuda mucho. Propongo realizar un cambio a esta clase
     * y a la estructura de "conocimientos". En principio que publicTransport conozca
     * su linea y sus paradas de inicio y fin, luego que Line conozca la lista de
     * paradas. Asi publicTransport le pregunta a la Linea sobre la distancia entre 2
     * paradas (inicio y fin) y Linea realiza las validaciones y pregunta a cada parada
     * entre [inicio, fin) para luego sumar esas distancias.
     */
    //TODO validate startStop has startLocation
    //TODO validate endStop has endLocation
    return line.getDistanceBetween(this.startStop, this.endStop);
  }
}
