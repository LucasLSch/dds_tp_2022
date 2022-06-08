package API.georef;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeoRefService {

  @GET("distancia")
  Call<Distance> distance(@Query("localidadOringenId") int originDistrct,
                          @Query("calleOrigen") String originStreet,
                          @Query("alturaOrigen") String originHeight,
                          @Query("localidadDestinoId") int destinyDistrct,
                          @Query("calleDestino") String destinyStreet,
                          @Query("alturaDestino") String destinyHeight);
}
