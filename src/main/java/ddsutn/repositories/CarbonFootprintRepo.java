package ddsutn.repositories;

import ddsutn.domain.measurements.CarbonFootprint;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarbonFootprintRepo extends CrudRepository<CarbonFootprint, Long> {

  @Query(value = "SELECT * FROM carbon_footprint WHERE organization_id = ?", nativeQuery = true)
  List<CarbonFootprint> findByOrganization(Long id);

  @Query(value = "SELECT * FROM carbon_footprint WHERE member_id = ?", nativeQuery = true)
  List<CarbonFootprint> findByMember(Long id);

  @Query(value = "SELECT * FROM carbon_footprint WHERE activity_data_id = ?", nativeQuery = true)
  List<CarbonFootprint> findByActivityData(Long id);

  @Query(value = "SELECT * FROM carbon_footprint WHERE territorial_sector_id = ?", nativeQuery = true)
  List<CarbonFootprint> findByTerritorialSector(Long id);

}
