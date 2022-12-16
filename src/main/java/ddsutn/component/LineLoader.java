package ddsutn.component;

import ddsutn.domain.journey.transport.Line;
import ddsutn.domain.journey.transport.PublicTransportType;
import ddsutn.domain.journey.transport.Stop;
import ddsutn.domain.location.Distance;
import ddsutn.domain.location.District;
import ddsutn.domain.location.Location;
import ddsutn.domain.measurements.unit.BaseUnit;
import ddsutn.domain.measurements.unit.Proportionality;
import ddsutn.domain.measurements.unit.Unit;
import ddsutn.services.LineSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class LineLoader implements ApplicationRunner {

  @Autowired
  private LineSvc lineSvc;

  private static Unit kilometer(Proportionality prop) {
    return new Unit(BaseUnit.METER, 3, prop);
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {

    lineSvc.deleteAll();

    if (!this.lineSvc.findAll().isEmpty()) {
      return;
    }

//    District dt = new District(437824328);
//
//    Stop stop1 = new Stop(
//            new Location(dt, "calle", "123"),
//            new Distance(15, kilometer(Proportionality.DIRECT))
//    );
//    Stop stop2 = new Stop(
//            new Location(dt, "street", "456"),
//            new Distance(3, kilometer(Proportionality.DIRECT))
//    );
//    Stop stop3 = new Stop(
//            new Location(dt, "street", "456"),
//            new Distance(15, kilometer(Proportionality.DIRECT))
//    );
//    Stop stop4 = new Stop(
//            new Location(dt, "calle", "123"),
//            new Distance(1, kilometer(Proportionality.DIRECT))
//    );
//
//    Line[] lines = {
//            new Line(Arrays.asList(stop1, stop2), "Roca - Ida", PublicTransportType.TRAIN),
//            new Line(Arrays.asList(stop3, stop4), "Roca - Vuelta", PublicTransportType.TRAIN)
//    };
//
//    lineSvc.saveAll(Arrays.asList(lines));

  }
}
