package ddsutn.repositories;

import ddsutn.domain.contact.Contact;

public class ContactRepo extends CrudImpl<Contact> {

  // --- Singleton --- //

  private static ContactRepo instance = null;

  private ContactRepo() {
    try {
      this.type = (Class<Contact>) Class.forName("ddsutn.domain.contact.Contact");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static ContactRepo getInstance() {
    if (instance == null) {
      instance = new ContactRepo();
      instance.initEntityManager();
    }
    return instance;
  }

  @Override
  Object getId(Contact someEntity) {
    return someEntity.getId();
  }
}
