package ddsutn.services;

import ddsutn.domain.organization.Member;
import ddsutn.repositories.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberSvc {

  @Autowired
  private MemberRepo memberRepo;

  public Member findById(Long id) {
    return this.memberRepo.findById(id).orElse(null);
  }
}
