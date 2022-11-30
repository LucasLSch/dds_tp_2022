package ddsutn.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GuideController {

  private final String guideHtml = "/home/home"; //TODO fix

  @GetMapping("/guiaRecomendaciones")
  public ModelAndView showRecomendationsGuide() {
    ModelAndView mav = new ModelAndView();
    mav.setStatus(HttpStatus.NOT_FOUND);
    return mav;
  }

}
