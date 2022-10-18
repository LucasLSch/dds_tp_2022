package ddsutn.repositories;

import ddsutn.domain.contact.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepo extends CrudRepository<Contact, Long> {

}
