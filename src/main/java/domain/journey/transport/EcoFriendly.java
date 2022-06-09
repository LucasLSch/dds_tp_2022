package domain.journey.transport;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EcoFriendly extends Transport {

  private EcoFriendlyType efType;

  @Override
  public Boolean isShareable() {
    return false;
  }
}
