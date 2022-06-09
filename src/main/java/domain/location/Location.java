package domain.location;

import java.io.IOException;
import services.georef.Distance;
import services.georef.GeoRefAdapter;

public class Location {

  private Integer district;
  private String street;
  private String height;

  public Location(Integer someDistrict, String someStreet, String someHeight) {
    this.district = someDistrict;
    this.street = someStreet;
    this.height = someHeight;
  }

  public Distance getDistanceTo(Location someLocation) throws IOException {
    GeoRefAdapter adapter = GeoRefAdapter.getInstance();
    return adapter.getDistance(this, someLocation);
  }

  public Integer getDistrict() {
    return district;
  }

  public String getStreet() {
    return street;
  }

  public String getHeight() {
    return height;
  }
}
