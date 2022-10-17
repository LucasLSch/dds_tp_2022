package ddsutn.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GuideController {

  @GetMapping("/guiaRecomendaciones")
  public String showRecomendationsGuide() {
    return "simular que esto es una guia muy completa sobre buenas costumbres del medio ambiente";
  }

}
