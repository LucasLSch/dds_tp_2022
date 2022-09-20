package repositories;

import domain.contact.Contact;

public class ContactRepo extends CrudImpl<Contact> {

  // --- Singleton --- //

  private static ContactRepo instance = null;

  private ContactRepo() {
    this.type = new Contact();
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
