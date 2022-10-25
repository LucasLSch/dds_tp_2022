package ddsutn.controllers;

import ddsutn.domain.measurements.ActivityData;
import ddsutn.domain.organization.Member;
import ddsutn.services.MemberSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/member")
public class MemberController {

  @Autowired
  private MemberSvc memberSvc;

  @GetMapping("")
  public String showAllMembers(Model model) {
    return "simlar que te muestro los miembros xdxd";
  }

  @GetMapping("/{id}")
  public String showOrganizatinoById(@PathVariable Long id, Model model) {
    Member read = memberSvc.findById(id);
    return "Se obtuvo el miembro de nombre " + read.getName() + " " + read.getSurname();
  }

  @GetMapping("/{id}/trayectos")
  public String showJourneys(@PathVariable Long id, Model model) {
    return "simular que te muestro los trayectos del miembro " + id + "xdxdxd";
  }

  @GetMapping("/registrarTrayecto")
  public String registerJourney(Model model) {
    return "Estas por registrar unTrayecto";
  }

  @PostMapping("/registrarTrayecto")
  public String registerJourney(@RequestBody ActivityData ad, Model model) {
    return "Agregaste un trayecto :D";
  }

}
