package ddsutn.controllers;

import ddsutn.domain.measurements.ActivityData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class CfCalculatorController {

  @GetMapping("/calculadoraHC")
  public ModelAndView useCalculator() {
    return new ModelAndView("CalculadoraHc.html");
  }

  @PostMapping("/calculadoraHC")
  public String useCalculator(@RequestBody ActivityData ad) {
    return "Calculaste la huella de carbono";
  }

}
