package domain.location;

import java.io.IOException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import services.georef.GeoRefAdapter;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "location")
public class Location {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Embedded
  private District district;

  @Column(name = "street_name")
  private String street;

  @Column(name = "street_height")
  private String height;

  public Distance getDistanceTo(Location someLocation) throws IOException {
    GeoRefAdapter adapter = GeoRefAdapter.getInstance();
    return adapter.getDistance(this, someLocation);
  }

  public Location(District district, String street, String height) {
    this.district = district;
    this.street = street;
    this.height = height;
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
