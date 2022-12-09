package ddsutn.domain.location;

import ddsutn.services.georef.GeoRefAdapter;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.IOException;

@NoArgsConstructor
@Getter
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


  public Location(District district, String street, String height) {
    this.district = district;
    this.street = street;
    this.height = height;
  }

  public static Location getLocationFor(String startingLocation) {
    //TODO arreglar, guardar datos de la api etc
    String[] streetArray = startingLocation.split("-");
    String foundStreet = streetArray[0];
    String foundHeight = streetArray[1];
    District foundDistrictXD = new District(3677); //Harcodeado en General LAVALLE
    return new Location(foundDistrictXD, foundStreet, foundHeight);
  }

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

  public String print() {
    return this.getStreet() + " " + this.getHeight();
  }

}
