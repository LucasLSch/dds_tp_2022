package ddsutn.component;

import ddsutn.domain.organization.DocType;
import ddsutn.domain.organization.Member;
import ddsutn.security.user.Administrator;
import ddsutn.security.user.OrgAdminUser;
import ddsutn.security.user.StandardUser;
import ddsutn.services.UserSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserLoader implements ApplicationRunner {

  @Autowired
  private UserSvc userSvc;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public void run(ApplicationArguments args) throws Exception {

    if (userSvc.findAll().size() > 1) {
      return;
    }

    String encodedPassword = bCryptPasswordEncoder.encode("Contr@5enia");

    Member testMember = new Member("suser", "resus", DocType.DNI, "99999999");

    StandardUser testSUser = new StandardUser("myUser", encodedPassword, testMember);

    userSvc.save(testSUser);

    Administrator admin = new Administrator("admin", encodedPassword);

    userSvc.save(admin);
  }
}
