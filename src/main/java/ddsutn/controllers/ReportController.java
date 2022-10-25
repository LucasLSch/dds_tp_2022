package ddsutn.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {

  @GetMapping("/reportes")
  public String showReports(Model model) {
    return "simular que te devuelvo el reporte que querias segun los query params que no existen";
  }

}
