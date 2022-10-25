package ddsutn.services;

import ddsutn.repositories.UserRepo;
import ddsutn.security.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class UserSvc extends GenericSvcImpl<User, Long> {

  @Autowired
  private UserRepo userRepo;

  @Override
  public CrudRepository<User, Long> getRepo() {
    return this.userRepo;
  }

}
