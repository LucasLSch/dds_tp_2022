package ddsutn.domain;

import ddsutn.domain.contact.Contact;
import ddsutn.domain.contact.EmailNotification;
import ddsutn.domain.organization.DocType;
import ddsutn.domain.organization.Member;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import ddsutn.repositories.MemberRepo;

public class Main implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {

  private void metodo() {

//    Set<Unit> unitSet = new HashSet<>();
//    unitSet.add(new Unit(BaseUnit.METER, 3, Proportionality.DIRECT));
//    unitSet.add(new Unit(BaseUnit.SECOND, 0, Proportionality.INVERSE));

    //CarbonFootprint miCf = new CarbonFootprint(70.0, unitSet, LocalDate.now());

//    List<CarbonFootprint> listaCF = new ArrayList<>();
//    listaCF.add(new CarbonFootprint(70.0, unitSet));
//    listaCF.add(new CarbonFootprint(80.0, unitSet));
//    listaCF.add(new CarbonFootprint(90.0, unitSet));
//    listaCF.add(new CarbonFootprint(10.0, unitSet));
//    listaCF.add(new CarbonFootprint(20.0, unitSet));

    Member unMiembro = new Member("pol", "icia", DocType.ID, "73737382");
    Contact unContacto = new Contact();
    unContacto.setEmail("policia@gmail.com");
    unContacto.setPhoneNumber("911");
    unContacto.setMethod(new EmailNotification());
    unMiembro.addContact(unContacto);

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
