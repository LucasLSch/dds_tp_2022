package ddsutn.controllers;

import ddsutn.domain.measurements.ActivityData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CfCalculatorController {

  @GetMapping("/calculadoraHC")
  public String useCalculator() {
    return "Estas por calcular una huella de carbono";
  }

  @PostMapping("/calculadoraHC")
  public String useCalculator(@RequestBody ActivityData ad) {
    return "Calculaste la huella de carbono";
  }

}
