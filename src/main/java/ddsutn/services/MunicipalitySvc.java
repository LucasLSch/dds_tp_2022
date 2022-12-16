package ddsutn.services;

import ddsutn.domain.location.Municipality;
import ddsutn.repositories.MunicipalityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class MunicipalitySvc extends GenericSvcImpl<Municipality, Integer> {

  @Autowired
  private MunicipalityRepo municipalityRepo;

  @Override
  public CrudRepository<Municipality, Integer> getRepo() {
    return this.municipalityRepo;
  }

}
