package ddsutn.controllers;

import ddsutn.domain.measurements.ActivityData;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/member")
public class MemberController {

  @GetMapping("")
  public String showAllMembers() {
    return "simlar que te muestro los miembros xdxd";
  }

  @GetMapping("/{id}")
  public String showOrganizatinoById(@PathVariable Long id) {
    return "simular que aca esta el miembro " + id;
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
