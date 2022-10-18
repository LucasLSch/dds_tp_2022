package ddsutn.repositories;

import ddsutn.domain.organization.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepo extends CrudRepository<Member, Long> {

}
