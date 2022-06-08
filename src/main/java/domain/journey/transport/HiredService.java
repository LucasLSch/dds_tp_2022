package domain.journey.transport;

public class HiredService extends Transport {

  private HiredServiceType hsType;
  private String serviceName;

  public HiredService(HiredServiceType someType, String someServiceName) {
    this.hsType = someType;
    this.serviceName = someServiceName;
  }

  @Override
  public Boolean isShareable() {
    return true;
  }
}
