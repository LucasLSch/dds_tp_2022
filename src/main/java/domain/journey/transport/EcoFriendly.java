package domain.journey.transport;

public class EcoFriendly implements Transport {

  private EcoFriendlyType efType;

  @Override
  public Boolean isShareable() {
    return false;
  }
}
