package ddsutn.repositories;

import ddsutn.domain.location.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepo extends CrudRepository<Country, Integer> {
}
