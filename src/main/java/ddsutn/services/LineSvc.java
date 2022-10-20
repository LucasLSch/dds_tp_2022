package ddsutn.services;

import ddsutn.domain.journey.transport.Line;
import ddsutn.repositories.LineRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LineSvc {

  @Autowired
  private LineRepo lineRepo;

  public void saveAll(List<Line> lines) {
    this.lineRepo.saveAll(lines);
  }

}
