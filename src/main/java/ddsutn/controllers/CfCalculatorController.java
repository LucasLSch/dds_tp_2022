package ddsutn.controllers;

import ddsutn.domain.measurements.ConsumptionType;
import ddsutn.services.ConsumptionTypeSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CfCalculatorController {

  private final String calculatorHtml = "/cfCalculator/cfCalculator";


  @Autowired
  private ConsumptionTypeSvc consumptionTypeSvc;

  private static String notOptionSelectedOption() {
    return "-- Seleccione una Opción --";
  }

  @GetMapping("/calculadoraHC")
  public ModelAndView useCalculator(
      @RequestParam(value = "ct", required = false) String ct,
      @RequestParam(value = "p", required = false) String p,
      @RequestParam(value = "cv", required = false) Double cv) {

    ModelAndView mav = new ModelAndView(calculatorHtml);

    Double result = 0d;

    if (ct != null && !ct.equals(notOptionSelectedOption()) && p != null && cv != null) {
      //TODO ver de manejar mejor los params no enviados con Optional o algo

      ConsumptionType ctObj = this.consumptionTypeSvc
          .findAllByCondition(consumptionType -> consumptionType.print().equals(ct))
          .stream()
          .findFirst()
          .orElse(null);

      if (ctObj == null) { //TODO same arriba
        return mav;
      }

      result = ctObj.calculateFor(cv);
    }

    mav.addObject("consumptionType", new ConsumptionType());
    mav.addObject("result", result);
    mav.addObject("allConsumptionTypes", this.consumptionTypeList());
    return mav;
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

}
