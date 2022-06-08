package domain.journey.transport;

public class EcoFriendly extends Transport {

  private EcoFriendlyType efType;

  @Override
  public Boolean isShareable() {
    return false;
  }
}
