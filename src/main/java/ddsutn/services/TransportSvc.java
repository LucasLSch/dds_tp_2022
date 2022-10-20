package ddsutn.services;

import ddsutn.domain.journey.transport.Line;
import ddsutn.domain.journey.transport.Transport;
import ddsutn.repositories.TransportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportSvc {

  @Autowired
  private TransportRepo transportRepo;

  public void saveAll(List<Transport> transports) {
    this.transportRepo.saveAll(transports);
  }

}
