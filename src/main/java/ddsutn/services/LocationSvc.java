package ddsutn.services;

import ddsutn.domain.location.Location;
import ddsutn.repositories.LocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class LocationSvc extends GenericSvcImpl<Location, Long> {

  @Autowired
  private LocationRepo locationRepo;

  @Override
  public CrudRepository<Location, Long> getRepo() {
    return this.locationRepo;
  }

}
