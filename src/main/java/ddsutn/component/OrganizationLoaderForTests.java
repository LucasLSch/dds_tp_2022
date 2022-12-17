package ddsutn.component;

import ddsutn.domain.location.District;
import ddsutn.domain.location.Location;
import ddsutn.domain.organization.OrgType;
import ddsutn.domain.organization.Organization;
import ddsutn.security.user.OrgAdminUser;
import ddsutn.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Order(4)
public class OrganizationLoaderForTests implements ApplicationRunner {

  @Autowired
  private OrganizationSvc organizationSvc;

  @Autowired
  private UserSvc userSvc;

  @Autowired
  private DistrictSvc districtSvc;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public void run(ApplicationArguments args) throws Exception {

    String encodedPassword = bCryptPasswordEncoder.encode("Contr@5enia");

    if (this.organizationSvc.findAll().isEmpty()) {
      District dt = districtSvc.findById(502);

      Organization org1 = new Organization("Doodle", new Location(dt, "calle", "123"), "algo", OrgType.COMPANY);
      Organization org2 = new Organization("HeadBook", new Location(dt, "street", "456"), "otroAlgo", OrgType.COMPANY);
      Organization org3 = new Organization("MeTube", new Location(dt, "rue", "789"), "nuse", OrgType.COMPANY);

      org1.createSector("banana");
      org1.createSector("manzana");

      Organization[] orgs = {
          org2,
          org3
      };

      OrgAdminUser orgCeo = new OrgAdminUser("orgAdmin", encodedPassword, org1);

      this.userSvc.save(orgCeo);

      this.organizationSvc.saveAll(Arrays.asList(orgs));
    }

  }

}

