package ddsutn.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ReportController {

  @GetMapping("/reportes")
  public String showReports() {
    return "simular que te devuelvo el reporte que querias segun los query params que no existen xD";
  }

}
