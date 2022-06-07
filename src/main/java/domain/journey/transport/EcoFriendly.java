package domain.journey.transport;

public class EcoFriendly implements Transport {

  private EcoFriendlyType efType;

  @Override
  public boolean isShareable() {
    return false;
  }
}
