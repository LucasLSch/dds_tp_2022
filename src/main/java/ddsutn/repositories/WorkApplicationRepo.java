package ddsutn.repositories;

import ddsutn.domain.organization.workApplication.WorkApplication;
import ddsutn.domain.organization.workApplication.WorkApplicationId;
import org.springframework.data.repository.CrudRepository;

public interface WorkApplicationRepo extends CrudRepository<WorkApplication, WorkApplicationId> {
}
