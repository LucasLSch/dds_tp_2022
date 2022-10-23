package ddsutn.controllers;

import ddsutn.domain.measurements.ActivityData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MeasurmentsController {

  @GetMapping("/registrarMediciones")
  public ModelAndView registerMeasurements() {
    return new ModelAndView("RegistrarMedicion.html");
  }

  @PostMapping("/registrarMediciones")
  public String registerMeasurements(@RequestBody ActivityData ad) {
    return "Agregaste un activity data";
  }

}
