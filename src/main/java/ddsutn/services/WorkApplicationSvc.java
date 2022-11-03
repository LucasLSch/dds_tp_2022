package ddsutn.services;

import ddsutn.domain.organization.workApplication.WorkApplication;
import ddsutn.domain.organization.workApplication.WorkApplicationId;
import ddsutn.domain.organization.workApplication.WorkApplicationState;
import ddsutn.repositories.WorkApplicationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkApplicationSvc extends GenericSvcImpl<WorkApplication, WorkApplicationId> {

  @Autowired
  private WorkApplicationRepo repo;

  @Override
  public CrudRepository<WorkApplication, WorkApplicationId> getRepo() {
    return repo;
  }

  public List<WorkApplication> getPendingApplications() {
    return this.findAllByCondition(wa -> wa.stateIs(WorkApplicationState.PENDING));
  }

}
