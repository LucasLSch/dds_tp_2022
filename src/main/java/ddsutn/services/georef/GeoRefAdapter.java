package ddsutn.services.georef;

import ddsutn.domain.location.*;
import ddsutn.domain.measurements.unit.BaseUnit;
import ddsutn.domain.measurements.unit.Proportionality;
import ddsutn.domain.measurements.unit.Unit;
import ddsutn.services.CountrySvc;
import ddsutn.services.DistrictSvc;
import ddsutn.services.MunicipalitySvc;
import ddsutn.services.ProvinceSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class GeoRefAdapter {

  private static final String urlAPI = "https://ddstpa.com.ar/api/";
  private static GeoRefAdapter instance = null;
  private GeoRefService api;
//
//  @Autowired
//  private CountrySvc countrySvc;
//
//  @Autowired
//  private ProvinceSvc provinceSvc;
//
//  @Autowired
//  private MunicipalitySvc municipalitySvc;
//
//  @Autowired
//  private DistrictSvc districtSvc;


  // --- Singleton --- //
  private final Retrofit retrofit;

  private GeoRefAdapter() {
    this.retrofit = new Retrofit.Builder()
        .baseUrl(urlAPI)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  // ----------------- //

  public static GeoRefAdapter getInstance() {
    if (instance == null) {
      instance = new GeoRefAdapter();
    }
    return instance;
  }

  public Distance getDistance(Location origin, Location destination) throws IOException {

    this.api = retrofit.create(GeoRefService.class);
    Call<DistanceResponse> distanceResponseCall = this.api.distance(
        origin.getDistrict().getId(),
        origin.getStreet(),
        origin.getHeight(),
        destination.getDistrict().getId(),
        destination.getStreet(),
        destination.getHeight());
    Response<DistanceResponse> response = distanceResponseCall.execute();

    Unit unit = this.getUnitOfString(response.body().unidad);
    return new Distance(response.body().getValor(), unit);
  }

  private Unit getUnitOfString(String unit) {

    String exponentString = unit.substring(0, unit.length() - 2).toLowerCase(Locale.ROOT);
    int exponent = 0;

    switch (exponentString) {
      case "m":
        exponent = -3;
        break;
      case "c":
        exponent = -2;
        break;
      case "d":
        exponent = -1;
        break;
      case "da":
        exponent = 1;
        break;
      case "h":
        exponent = 2;
        break;
      case "k":
        exponent = 3;
        break;
      default:
        exponent = 0;
        break;
    }

    return new Unit(BaseUnit.METER, exponent, Proportionality.DIRECT);

  }

  public List<Country> getAllCountries(CountrySvc countrySvc) throws IOException {
    this.api = retrofit.create(GeoRefService.class);

    int offset = 1;
    List<Country> countries = new ArrayList<>();

    Call<List<CountryResponse>> countriesResponseCall;
    Response<List<CountryResponse>> response;

    countriesResponseCall = this.api.countries(offset);
    response = countriesResponseCall.execute();

    while (!response.body().isEmpty()) {
      offset++;
      countries.addAll(response.body().stream().map(cr -> cr.toCountry(countrySvc)).collect(Collectors.toList()));
      countriesResponseCall = this.api.countries(offset);
      response = countriesResponseCall.execute();
    }

    return countries;
  }

  public List<Province> getAllProvinces(ProvinceSvc provinceSvc, CountrySvc countrySvc) throws IOException {
    this.api = retrofit.create(GeoRefService.class);

    int offset = 1;
    List<Province> provinces = new ArrayList<>();

    Call<List<ProvinceResponse>> provincesResponseCall;
    Response<List<ProvinceResponse>> response;

    provincesResponseCall = this.api.allProvinces(offset);
    response = provincesResponseCall.execute();

    while (!response.body().isEmpty()) {
      offset++;
      provinces.addAll(response.body().stream().map(pr -> pr.toProvince(provinceSvc, countrySvc)).collect(Collectors.toList()));
      provincesResponseCall = this.api.allProvinces(offset);
      response = provincesResponseCall.execute();
    }

    return provinces;
  }

  public List<Municipality> getAllMunicipalities(MunicipalitySvc municipalitySvc, ProvinceSvc provinceSvc) throws IOException {
    this.api = retrofit.create(GeoRefService.class);

    int offset = 1;
    List<Municipality> municipalities = new ArrayList<>();

    Call<List<MunicipalityResponse>> municipalitiesResponseCall;
    Response<List<MunicipalityResponse>> response;

    municipalitiesResponseCall = this.api.allMunicipalities(offset);
    response = municipalitiesResponseCall.execute();

    while (!response.body().isEmpty()) {
      offset++;
      municipalities.addAll(response.body().stream().map(mr -> mr.toMunicipality(municipalitySvc, provinceSvc)).collect(Collectors.toList()));
      municipalitiesResponseCall = this.api.allMunicipalities(offset);
      response = municipalitiesResponseCall.execute();
    }

    return municipalities;
  }

  public List<District> getAllDistrict(DistrictSvc districtSvc, MunicipalitySvc municipalitySvc) throws IOException {
    this.api = retrofit.create(GeoRefService.class);

    int offset = 1;
    List<District> districts = new ArrayList<>();

    Call<List<DistrictResponse>> districtsResponseCall;
    Response<List<DistrictResponse>> response;

    districtsResponseCall = this.api.allDistricts(offset);
    response = districtsResponseCall.execute();

    while (!response.body().isEmpty()) {
      offset++;
      districts.addAll(response.body().stream().map(dr -> dr.toDistrict(districtSvc, municipalitySvc)).collect(Collectors.toList()));
      districtsResponseCall = this.api.allDistricts(offset);
      response = districtsResponseCall.execute();
    }

    return districts;
  }

}
