package ddsutn.component;

import ddsutn.domain.location.Country;
import ddsutn.domain.location.District;
import ddsutn.domain.location.Municipality;
import ddsutn.domain.location.Province;
import ddsutn.services.*;
import ddsutn.services.georef.GeoRefAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@Order(1)
public class GeoRefLoader implements ApplicationRunner {

  @Autowired
  private DistrictSvc districtSvc;

  @Autowired
  private MunicipalitySvc municipalitySvc;

  @Autowired
  private ProvinceSvc provinceSvc;

  @Autowired
  private CountrySvc countrySvc;

  @Override
  public void run(ApplicationArguments args) throws IOException {

    if (this.countrySvc.findAll().isEmpty()) {
      GeoRefAdapter geoRefAdapter = GeoRefAdapter.getInstance();

      List<Country> countries = geoRefAdapter.getAllCountries(countrySvc);
      this.countrySvc.saveAll(countries);

      List<Province> provinces = geoRefAdapter.getAllProvinces(provinceSvc, countrySvc);
      this.provinceSvc.saveAll(provinces);

      List<Municipality> municipalities = geoRefAdapter.getAllMunicipalities(municipalitySvc, provinceSvc);
      this.municipalitySvc.saveAll(municipalities);

      List<District> districts = geoRefAdapter.getAllDistrict(districtSvc, municipalitySvc);
      this.districtSvc.saveAll(districts);
    }

  }


}
