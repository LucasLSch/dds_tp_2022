package ddsutn.services;

import ddsutn.domain.journey.transport.Transport;
import ddsutn.repositories.TransportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class TransportSvc extends GenericSvcImpl<Transport, Long> {

  @Autowired
  private TransportRepo transportRepo;

  @Override
  public CrudRepository<Transport, Long> getRepo() {
    return this.transportRepo;
  }

}
