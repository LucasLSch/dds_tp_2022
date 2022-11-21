package ddsutn.repositories;

import ddsutn.security.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Long>, JpaRepository<User, Long> {

  User findByUsername(String username);

}
