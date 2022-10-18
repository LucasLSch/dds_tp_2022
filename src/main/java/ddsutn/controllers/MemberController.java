package ddsutn.controllers;

import ddsutn.domain.measurements.ActivityData;
import ddsutn.domain.organization.Member;
import ddsutn.services.MemberSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/member")
public class MemberController {

  @Autowired
  private MemberSvc memberSvc;

  @GetMapping("")
  public String showAllMembers() {
    return "simlar que te muestro los miembros xdxd";
  }

  @GetMapping("/{id}")
  public String showOrganizatinoById(@PathVariable Long id) {
    Member read = memberSvc.findById(id);
    return "Se obtuvo el miembro de nombre " + read.getName() + " " + read.getSurname();
  }

  @GetMapping("/{id}/trayectos")
  public String showJourneys(@PathVariable Long id) {
    return "simular que te muestro los trayectos del miembro " + id + "xdxdxd";
  }

  @GetMapping("/registrarTrayecto")
  public String registerJourney() {
    return "Estas por registrar unTrayecto";
  }

  @PostMapping("/registrarTrayecto")
  public String registerJourney(@RequestBody ActivityData ad) {
    return "Agregaste un trayecto :D";
  }

}
