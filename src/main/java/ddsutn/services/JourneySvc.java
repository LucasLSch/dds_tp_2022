package ddsutn.services;

import ddsutn.domain.journey.Journey;
import ddsutn.repositories.JourneyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class JourneySvc extends GenericSvcImpl<Journey, Long> {

  @Autowired
  private JourneyRepo journeyRepo;

  @Override
  public CrudRepository<Journey, Long> getRepo() {
    return journeyRepo;
  }

}
