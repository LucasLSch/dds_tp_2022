package ddsutn.controllers;

import ddsutn.services.OrganizationSvc;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/organizaciones")
public class OrganizationController {

  @GetMapping("")
  public String showOrganizations(Model model) {
    return "buscarOrganizacion";
  }

  @GetMapping("/search/{param}")//refrescarme como hacer un search
  public String searchOrganizacion(@PathVariable String param, Model model) {
    OrganizationSvc svc = new OrganizationSvc();
    model.addAttribute("organizations", svc.findAllByCondition((organization)->organization.getSocialObjective().equals(param)));
    return "buscarOrganizacion";
  }

  @GetMapping("/{id}")
  public String showOrganizatinoById(@PathVariable Long id, Model model) {
    return "detalleOrganizacion";
    //add atribute objeto org, buscarlo con id
  }

  @GetMapping("/{id}/solicitar")
  public String apply(@PathVariable Long id, Model model) {
    return "simular que solicita para org " + id;
  }

  @GetMapping("/{id}/aceptar")
  public String accept(@PathVariable Long id, Model model) {//cirtcular view path, que esta pasando
    return "aceptarMiembro";
  }

}
