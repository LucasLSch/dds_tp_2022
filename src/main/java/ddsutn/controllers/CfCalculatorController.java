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

    System.out.println(ct);
    System.out.println(p);
    System.out.println(cv);


    if(ct != null && !ct.equals(notOptionSelectedOption()) && p != null && cv != null) {
      //TODO ver de manejar mejor los params no enviados con Optional o algo

      ConsumptionType ctObj = this.consumptionTypeSvc
          .findAllByCondition(consumptionType -> consumptionType.print().equals(ct))
          .stream()
          .findFirst()
          .orElse(null);

      if(ctObj == null) { //TODO same arriba
        return "calculadoraHC";
      }

      result = ctObj.calculateFor(cv);
    }

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
        .map(ConsumptionType::print)
        .collect(Collectors.toList()));

    return finalList;
  }

  private static String notOptionSelectedOption() {
    return "-- Seleccione una Opción --";
  }

}
