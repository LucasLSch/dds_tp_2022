package ddsutn.component;

import ddsutn.domain.location.District;
import ddsutn.domain.location.Location;
import ddsutn.domain.organization.OrgType;
import ddsutn.domain.organization.Organization;
import ddsutn.services.OrganizationSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class OrganizationLoaderForTests implements ApplicationRunner {

  @Autowired
  private OrganizationSvc organizationSvc;

  @Override
  public void run(ApplicationArguments args) throws Exception {

    if (!this.organizationSvc.findAll().isEmpty()) {
      return;
    }

    District dt = new District(1);

    Organization org1 = new Organization("Doodle", new Location(dt, "calle", "123"), "algo", OrgType.COMPANY);
    Organization org2 = new Organization("HeadBook", new Location(dt, "street", "456"), "otroAlgo", OrgType.COMPANY);
    Organization org3 = new Organization("MeTube", new Location(dt, "rue", "789"), "nuse", OrgType.COMPANY);

    org1.createSector("banana");
    org1.createSector("manzana");

    Organization[] orgs = {
        org1,
        org2,
        org3
    };

    this.organizationSvc.saveAll(Arrays.asList(orgs));

  }

}
