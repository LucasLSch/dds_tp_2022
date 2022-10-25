package ddsutn.services;

import ddsutn.domain.organization.Member;
import ddsutn.repositories.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberSvc extends GenericSvcImpl<Member, Long> {

  @Autowired
  private MemberRepo memberRepo;

  @Override
  public CrudRepository getRepo() {
    return this.memberRepo;
  }

}
