package domain.location;

import domain.journey.Distance;
import java.io.IOException;
import lombok.AllArgsConstructor;
import services.georef.GeoRefAdapter;

@AllArgsConstructor
public class Location {

  private Integer district;
  private String street;
  private String height;

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
