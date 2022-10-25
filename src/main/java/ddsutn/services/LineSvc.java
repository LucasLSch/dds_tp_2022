package ddsutn.services;

import ddsutn.domain.journey.transport.Line;
import ddsutn.repositories.LineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class LineSvc extends GenericSvcImpl<Line, Long> {

  @Autowired
  private LineRepo lineRepo;

  @Override
  public CrudRepository<Line, Long> getRepo() {
    return this.lineRepo;
  }

}
