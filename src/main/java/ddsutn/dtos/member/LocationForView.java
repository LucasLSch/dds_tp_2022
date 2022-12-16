package ddsutn.dtos.member;

import ddsutn.domain.location.Location;
import ddsutn.services.DistrictSvc;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationForView {

  public String street;
  public String height;
  public String country;
  public String province;
  public String municipality;
  public String district;

  public LocationForView(Location someLocation) {
    this.street = someLocation.getStreet();
    this.height = someLocation.getHeight();
    //TODO ver si se necesita el resto
  }

  public Location toLocation(DistrictSvc districtSvc) {
    return new Location(districtSvc.findById(Integer.parseInt(district)), this.street, this.height);
    //TODO reparar
  }

}
