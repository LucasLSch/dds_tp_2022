package ddsutn.services;

import ddsutn.domain.location.Country;
import ddsutn.repositories.CountryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class CountrySvc extends GenericSvcImpl<Country, Integer> {

  @Autowired
  private CountryRepo countryRepo;

  @Override
  public CrudRepository<Country, Integer> getRepo() {
    return this.countryRepo;
  }

}
