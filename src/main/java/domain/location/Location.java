package domain.location;

public class Location {
  private Integer district;
  private String street;
  private String height;

  public Location(Integer someDistrict, String someStreet, String someHeight) {
    this.district = someDistrict;
    this.street = someStreet;
    this.height = someHeight;
  }

  public Integer getDistanceTo(Location someLocation) {
    DistanceCalculatorAPIAdapter adapter = new DistanceCalculatorAPIAdapter();
    return adapter.getDistance(this, someLocation);
  }
}
