package ddsutn.services;

import ddsutn.domain.location.Province;
import ddsutn.repositories.ProvinceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ProvinceSvc extends GenericSvcImpl<Province, Integer> {

  @Autowired
  private ProvinceRepo provinceRepo;

  @Override
  public CrudRepository<Province, Integer> getRepo() {
    return this.provinceRepo;
  }

}
