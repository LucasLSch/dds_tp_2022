package ddsutn.services;

import ddsutn.repositories.UserRepo;
import ddsutn.security.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSvc extends GenericSvcImpl<User, Long> implements UserDetailsService {

  @Autowired
  private UserRepo userRepo;

  @Override
  public CrudRepository<User, Long> getRepo() {
    return this.userRepo;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepo.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException(username);
    }
    return user;
  }
}
