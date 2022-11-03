package ddsutn.services;

import ddsutn.domain.measurements.ActivityData;
import ddsutn.repositories.ActivityDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ActivityDataSvc extends GenericSvcImpl<ActivityData, Long> {

  @Autowired
  private ActivityDataRepo activityDataRepo;

  @Override
  public CrudRepository<ActivityData, Long> getRepo() {
    return this.activityDataRepo;
  }
}
