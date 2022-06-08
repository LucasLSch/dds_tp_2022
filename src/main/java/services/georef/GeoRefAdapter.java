package services.georef;

import domain.location.Location;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class GeoRefAdapter {

  private GeoRefService api;
  private Retrofit retrofit;
  private static final String urlAPI = "https://ddstpa.com.ar/api/";


  // --- Singleton --- //

  private static GeoRefAdapter instance = null;

  public static GeoRefAdapter getInstance() {
    if(instance == null) {
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
    Call<Distance> distanceResponseCall = this.api.distance(
        origin.getDistrict(),
        origin.getStreet(),
        origin.getHeight(),
        destination.getDistrict(),
        destination.getStreet(),
        destination.getHeight());
    Response<Distance> response = distanceResponseCall.execute();
    return response.body();
  }

}
