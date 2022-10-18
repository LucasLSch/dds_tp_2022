package ddsutn.repositories;

import ddsutn.domain.organization.Sector;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorRepo extends CrudRepository<Sector, Long> {

}
