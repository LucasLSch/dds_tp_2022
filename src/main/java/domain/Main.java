package domain;

import domain.contact.Contact;
import domain.contact.EmailNotification;
import domain.measurements.CarbonFootprint;
import domain.measurements.unit.BaseUnit;
import domain.measurements.unit.Proportionality;
import domain.measurements.unit.Unit;
import domain.organization.DocType;
import domain.organization.Member;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import repositories.CarbonFootprintRepo;
import repositories.MemberRepo;

import java.time.LocalDate;
import java.util.*;

public class Main implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {

  private void metodo() {

    Set<Unit> unitSet = new HashSet<>();
    unitSet.add(new Unit(BaseUnit.METER, 3, Proportionality.DIRECT));
    unitSet.add(new Unit(BaseUnit.SECOND, 0, Proportionality.INVERSE));

    //CarbonFootprint miCf = new CarbonFootprint(70.0, unitSet, LocalDate.now());

    List<CarbonFootprint> listaCF = new ArrayList<>();
    listaCF.add(new CarbonFootprint(70.0, unitSet, LocalDate.now()));
    listaCF.add(new CarbonFootprint(80.0, unitSet, LocalDate.now()));
    listaCF.add(new CarbonFootprint(90.0, unitSet, LocalDate.now()));
    listaCF.add(new CarbonFootprint(10.0, unitSet, LocalDate.now()));
    listaCF.add(new CarbonFootprint(20.0, unitSet, LocalDate.now()));


    Member unMiembro = MemberRepo.getInstance().getById(2l);
//    Contact unContacto = new Contact();
//    unContacto.setEmail("otroContacto@gmail.com");
//    unContacto.setPhoneNumber("unTelefonoEnLetrasXD");
//    unContacto.setMethod(new EmailNotification());
//    unContacto.setMember(unMiembro);
//    unMiembro.addContact(unContacto);

    unMiembro.notify("hola");

//    MemberRepo.getInstance().save(unMiembro);


//
//    withTransaction(() -> {
//      entityManager().persist(miCf);
//    });
//
//    CarbonFootprintRepo.getInstance().saveAll(listaCF);
//    List<CarbonFootprint> listaCF = CarbonFootprintRepo.getInstance().getAll();
//    listaCF.forEach( cf -> System.out.format("Id: %d, value: %f%n", cf.getId(), cf.getValue()));
//    System.out.println(miCf.getId());
//    CarbonFootprintRepo.getInstance().deleteAll();

  }

  public static void main(String[] args) {
    new Main().metodo();
  }
}
