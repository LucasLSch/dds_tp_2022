package ddsutn.controllers;

import ddsutn.domain.measurements.ActivityData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CfCalculatorController {

  @GetMapping("/calculadoraHC")
  public String useCalculator(Model model) {
    return "calculadoraHC";
  }

  @PostMapping("/calculadoraHC")
  public String useCalculator(@RequestBody ActivityData ad, Model model) {
    return "Calculaste la huella de carbono";
  }

}
