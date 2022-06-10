package services.georef;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeoRefService {

  @GET("distancia")
  Call<DistanceResponse> distance(@Query("localidadOringenId") int originDistrct,
                                  @Query("calleOrigen") String originStreet,
                                  @Query("alturaOrigen") String originHeight,
                                  @Query("localidadDestinoId") int destinyDistrct,
                                  @Query("calleDestino") String destinyStreet,
                                  @Query("alturaDestino") String destinyHeight);

  @GET("paises")
  Call<CountryResponse> countries(@Query("offset") int offset);

  @GET("provincias")
  Call<ProvinceResponse> provinces(@Query("offset") int offset,
                                   @Query("paisId") int countryId);

  @GET("localidades")
  Call<DistrictResponse> localities(@Query("offset") int offset,
                                    @Query("municipioId") int munipialityId);

  @GET("municipios")
  Call<MunicipalityResponse> municipalities(@Query("offset") int offset,
                                            @Query("provinciaId") int provinceId);
}
