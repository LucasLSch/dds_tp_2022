package services.georef;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeoRefService {

  @GET("distancia")
  Call<Distance> distance(@Query("localidadOrigenId") int originDistrict,
                          @Query("calleOrigen") String originStreet,
                          @Query("alturaOrigen") String originHeight,
                          @Query("localidadDestinoId") int destinyDistrict,
                          @Query("calleDestino") String destinyStreet,
                          @Query("alturaDestino") String destinyHeight);
}
