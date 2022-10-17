package ddsutn.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/organizaciones")
public class OrganizationController {

  @GetMapping("")
  public String showOrganizations() {
    return "simular que aca estan las organizaciones :D";
  }

  @GetMapping("/{id}")
  public String showOrganizatinoById(@PathVariable Long id) {
    return "simular que aca esta la org " + id;
  }

  @GetMapping("/{id}/solicitar")
  public String apply(@PathVariable Long id) {
    return "simular que solicita para org " + id;
  }

  @GetMapping("/{id}/aceptar")
  public String accept(@PathVariable Long id) {
    return "simular que acepta miembro de org " + id;
  }


}
