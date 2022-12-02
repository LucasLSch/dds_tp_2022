package ddsutn.component;

import ddsutn.domain.journey.Journey;
import ddsutn.domain.journey.Leg;
import ddsutn.domain.journey.transport.*;
import ddsutn.domain.location.District;
import ddsutn.domain.location.Location;
import ddsutn.domain.organization.DocType;
import ddsutn.domain.organization.Member;
import ddsutn.domain.organization.OrgType;
import ddsutn.domain.organization.Organization;
import ddsutn.services.JourneySvc;
import ddsutn.services.LocationSvc;
import ddsutn.services.MemberSvc;
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

  @Autowired
  private MemberSvc memberSvc;

  @Autowired
  private LocationSvc locationSvc;

  @Autowired
  private JourneySvc journeySvc;

  @Override
  public void run(ApplicationArguments args) throws Exception {

    if (this.organizationSvc.findAll().isEmpty()) {
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



    if (this.memberSvc.findAll().isEmpty()) {

      Member member = new Member("esteMiembro", "esDePrueba", DocType.DNI, "12345678");

      District dt = new District(1);

      Leg[] legs = {
          new Leg(new Location(dt, "Rue1", "1"),
                  new Location(dt, "Rue1", "2"),
                  new EcoFriendly(EcoFriendlyType.BICYCLE)),

          new Leg(new Location(dt, "Rue1", "2"),
              new Location(dt, "Rue1", "3"),
              new ParticularVehicle(0.6, ParticularVehicleType.CAR, Fuel.OIL)),

          new Leg(new Location(dt, "Rue1", "3"),
              new Location(dt, "Rue1", "4"),
              new HiredService(0.3, HiredServiceType.APPLICATION, "SUBER"))
      };

      Journey journey = new Journey(Arrays.asList(legs));

      member.addJourney(journey);

      this.memberSvc.save(member);

    }


  }

}

