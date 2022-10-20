package ddsutn.domain;

import ddsutn.domain.contact.Contact;
import ddsutn.domain.contact.EmailNotification;
import ddsutn.domain.organization.DocType;
import ddsutn.domain.organization.Member;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

public class Main implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {

  private void metodo() {

    Contact unContacto = new Contact();
    unContacto.setEmail("policia@gmail.com");
    unContacto.setPhoneNumber("911");
    unContacto.setMethod(new EmailNotification());
    Member unMiembro = new Member("pol", "icia", DocType.ID, "73737382");
    unMiembro.addContact(unContacto);

  }

  public static void main(String[] args) {
    new Main().metodo();
  }
}
