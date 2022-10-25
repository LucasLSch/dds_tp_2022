package ddsutn.controllers;

import ddsutn.domain.measurements.ActivityData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MeasurmentsController {

  @GetMapping("/registrarMediciones")
  public String registerMeasurements(Model model) {
    return "registrarMediciones";
  }

  @PostMapping("/registrarMediciones")
  public String registerMeasurements(@RequestBody ActivityData ad, Model model) {
    return "Agregaste un activity data";
  }

}
