package domain.location;

import API.SwaggerHubInterface;
import retrofit2.Retrofit;

public class DistanceCalculatorAPIAdapter {

  private SwaggerHubInterface api;

  public DistanceCalculatorAPIAdapter() {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://ddstpa.com.ar")
        .build();
    this.api = retrofit.create(SwaggerHubInterface.class);
  }

  public Integer getDistance(Location origin, Location destiny) {
    //TODO
    return 100;
  }

}
