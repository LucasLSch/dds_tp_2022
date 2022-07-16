package domain.location;

import lombok.AllArgsConstructor;
import services.georef.GeoRefAdapter;

import java.io.IOException;

@AllArgsConstructor
public class Location {

  private District district;
  private String street;
  private String height;

  public Distance getDistanceTo(Location someLocation) throws IOException {
    GeoRefAdapter adapter = GeoRefAdapter.getInstance();
    return adapter.getDistance(this, someLocation);
  }

  public District getDistrict() {
    return district;
  }

  public String getStreet() {
    return street;
  }

  public String getHeight() {
    return height;
  }
}
