package ddsutn.services;

import ddsutn.repositories.UserRepo;
import ddsutn.security.user.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSvc {

  @Autowired
  private UserRepo userRepo;

  public void saveAll(List<User> users) {
    this.userRepo.saveAll(users);
  }
}
