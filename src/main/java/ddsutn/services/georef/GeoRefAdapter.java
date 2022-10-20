package ddsutn.services.georef;

import ddsutn.domain.location.Distance;
import ddsutn.domain.location.Location;
import ddsutn.domain.measurements.unit.BaseUnit;
import ddsutn.domain.measurements.unit.Proportionality;
import ddsutn.domain.measurements.unit.Unit;
import java.io.IOException;
import java.util.Locale;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GeoRefAdapter {

  private GeoRefService api;
  private Retrofit retrofit;
  private static final String urlAPI = "https://ddstpa.com.ar/api/";


  // --- Singleton --- //

  private static GeoRefAdapter instance = null;

  public static GeoRefAdapter getInstance() {
    if (instance == null) {
      instance = new GeoRefAdapter();
    }
    return instance;
  }

  // ----------------- //

  private GeoRefAdapter() {
    this.retrofit = new Retrofit.Builder()
            .baseUrl(urlAPI)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
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

    Unit unit = this.getUnitOfString(response.body().unit);
    return new Distance(response.body().getValue(), unit);
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

}
