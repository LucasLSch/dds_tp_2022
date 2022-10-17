package ddsutn.controllers;

import ddsutn.domain.measurements.ActivityData;
import ddsutn.domain.measurements.CarbonFootprint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeasurmentsController {

  @GetMapping("/registrarMediciones")
  public String registerMeasurements() {
    return "Estas por registrar mediciones";
  }

  @PostMapping("/registrarMediciones")
  public String registerMeasurements(@RequestBody ActivityData ad) {
    return "Agregaste un activity data";
  }

}
