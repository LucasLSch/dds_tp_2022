package ddsutn.services.georef;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import java.util.List;

public interface GeoRefService {

  @Headers("Authorization: Bearer sf6bewWg3jWqH+3QYKBmpdg4nmWk5ZXYj8c1/U/67I8=")
  @GET("distancia")
  Call<DistanceResponse> distance(@Query("localidadOringenId") int originDistrct,
                                  @Query("calleOrigen") String originStreet,
                                  @Query("alturaOrigen") String originHeight,
                                  @Query("localidadDestinoId") int destinyDistrct,
                                  @Query("calleDestino") String destinyStreet,
                                  @Query("alturaDestino") String destinyHeight);

  @Headers("Authorization: Bearer sf6bewWg3jWqH+3QYKBmpdg4nmWk5ZXYj8c1/U/67I8=")
  @GET("paises")
  Call<List<CountryResponse>> countries(@Query("offset") int offset);

  @Headers("Authorization: Bearer sf6bewWg3jWqH+3QYKBmpdg4nmWk5ZXYj8c1/U/67I8=")
  @GET("provincias")
  Call<List<ProvinceResponse>> allProvinces(@Query("offset") int offset);

  @Headers("Authorization: Bearer sf6bewWg3jWqH+3QYKBmpdg4nmWk5ZXYj8c1/U/67I8=")
  @GET("provincias")
  Call<List<ProvinceResponse>> provinces(@Query("offset") int offset,
                                         @Query("paisId") int countryId);

  @Headers("Authorization: Bearer sf6bewWg3jWqH+3QYKBmpdg4nmWk5ZXYj8c1/U/67I8=")
  @GET("localidades")
  Call<List<DistrictResponse>> allDistricts(@Query("offset") int offset);

  @Headers("Authorization: Bearer sf6bewWg3jWqH+3QYKBmpdg4nmWk5ZXYj8c1/U/67I8=")
  @GET("localidades")
  Call<List<DistrictResponse>> districts(@Query("offset") int offset,
                                         @Query("municipioId") int munipialityId);

  @Headers("Authorization: Bearer sf6bewWg3jWqH+3QYKBmpdg4nmWk5ZXYj8c1/U/67I8=")
  @GET("municipios")
  Call<List<MunicipalityResponse>> allMunicipalities(@Query("offset") int offset);

  @Headers("Authorization: Bearer sf6bewWg3jWqH+3QYKBmpdg4nmWk5ZXYj8c1/U/67I8=")
  @GET("municipios")
  Call<List<MunicipalityResponse>> municipalities(@Query("offset") int offset,
                                                  @Query("provinciaId") int provinceId);
}
