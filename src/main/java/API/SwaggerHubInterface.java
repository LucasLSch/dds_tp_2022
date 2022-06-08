package API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SwaggerHubInterface {
  @GET("api/distancia")
  Call<Integer> distance(@Path("localidadOringenId") Integer originDistrct,
                         @Path("calleOrigen") String originStreet,
                         @Path("alturaOrigen") String originHeight,
                         @Path("localidadDestinoId") Integer destinyDistrct,
                         @Path("calleDestino") String destinyStreet,
                         @Path("alturaDestino") String destinyHeight);
}
