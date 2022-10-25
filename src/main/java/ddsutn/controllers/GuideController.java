package ddsutn.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GuideController {

  @GetMapping("/guiaRecomendaciones")
  public String showRecomendationsGuide(Model model) {
    return "simular que esto es una guia muy completa sobre buenas costumbres del medio ambiente";
  }

}
