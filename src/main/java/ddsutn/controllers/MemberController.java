package ddsutn.controllers;

import ddsutn.domain.measurements.ActivityData;
import ddsutn.domain.organization.Member;
import ddsutn.dtos.MemberForView;
import ddsutn.services.MemberSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/miembros")
public class MemberController {

  @Autowired
  private MemberSvc memberSvc;

  @GetMapping("/{id}")
  public ModelAndView showMember(@PathVariable Long id) {
    ModelAndView mav = new ModelAndView();

    Member read = memberSvc.findById(id);

    if(read == null) {
      mav.setStatus(HttpStatus.NOT_FOUND);
      return mav;
    }

    MemberForView member = new MemberForView(read);
    mav.addObject("member", member);
    mav.setViewName("miembros/detalleMiembro");

    return mav;
  }

//
//  @GetMapping("/{id}/trayectos")
//  public String showJourneys(@PathVariable Long id, Model model) {
//    return "simular que te muestro los trayectos del miembro " + id + "xdxdxd";
//  }
//
//  @GetMapping("/registrarTrayecto")
//  public String registerJourney(Model model) {
//    return "Estas por registrar unTrayecto";
//  }
//
//  @PostMapping("/registrarTrayecto")
//  public String registerJourney(@RequestBody ActivityData ad, Model model) {
//    return "Agregaste un trayecto :D";
//  }
//


}
