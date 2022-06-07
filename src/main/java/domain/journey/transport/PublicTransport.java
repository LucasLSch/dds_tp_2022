package domain.journey.transport;

import java.util.List;

public class PublicTransport implements Transport {

  private List<Stop> stopList;

  @Override
  public Boolean isShareable() {
    return false;
  }
}
