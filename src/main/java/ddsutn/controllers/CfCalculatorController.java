package ddsutn.controllers;

import ddsutn.domain.measurements.ActivityData;
import ddsutn.domain.measurements.ConsumptionType;
import ddsutn.domain.measurements.unit.UnitExpression;
import ddsutn.services.ConsumptionTypeSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CfCalculatorController {

  @Autowired
  private ConsumptionTypeSvc consumptionTypeSvc;

  @GetMapping("/calculadoraHC")
  public String useCalculator(
      @RequestParam(value = "ct", required = false) String ct,
      @RequestParam(value = "p", required = false) String p,
      @RequestParam(value = "cv", required = false) Double cv,
      Model model) {

    Double result = 0d;



    if(ct != null && !ct.equals(notOptionSelectedOption()) && p != null && cv != null) {
      //TODO calcular el valor correcto
      //TODO ver de manejar mejor los params no enviados con Optional o algo
    }

    System.out.println(this.consumptionTypeList());

    model.addAttribute("consumptionType", new ConsumptionType());
    model.addAttribute("result", result);
    model.addAttribute("allConsumptionTypes", this.consumptionTypeList());
    return "calculadoraHC";

  }

  @PostMapping("/calculadoraHC")
  public String useCalculator(@RequestBody ActivityData ad, Model model) {
    return "Calculaste la huella de carbono";
  }


  private List<String> consumptionTypeList() {
    List<String> finalList = new ArrayList<>();

    finalList.add(notOptionSelectedOption());

    finalList.addAll(this.consumptionTypeSvc
        .findAll()
        .stream()
        .map(ct -> ct.getName() + " (" + UnitExpression.printUnits(ct.getUnits()) + ")")
        .collect(Collectors.toList()));

    return finalList;
  }

  private static String notOptionSelectedOption() {
    return "-- Seleccione una Opción --";
  }

}
