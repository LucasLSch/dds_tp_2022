package ddsutn.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

  private final String homeHtml = "/home/home";

  @GetMapping("/")
  public ModelAndView home() {
    ModelAndView mav = new ModelAndView(homeHtml);
    return mav;
  }

}
