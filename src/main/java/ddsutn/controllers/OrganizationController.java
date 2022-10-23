package ddsutn.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/organizaciones")
public class OrganizationController {

  @GetMapping("")
  public ModelAndView showOrganizations() {
    return new ModelAndView("BuscarOrganizacion.html");
  }
  @GetMapping("/{param}/{key}")//refrescarme como hacer un search
  public ModelAndView searchOrganizacion(@PathVariable String param,@PathVariable String key ) {
    return new ModelAndView("BuscarOrganizacion.html");
  }
  @GetMapping("/{id}")
  public ModelAndView showOrganizatinoById(@PathVariable Long id) {
    return new ModelAndView("DetalleOrganizacion.html");
    //add atribute objeto org, buscarlo con id
  }

  @GetMapping("/{id}/solicitar")
  public String apply(@PathVariable Long id) {
    return "simular que solicita para org " + id;
  }

  @GetMapping("/{id}/aceptar")
  public ModelAndView accept(@PathVariable Long id) {//cirtcular view path, que esta pasando
    return new ModelAndView("aceptarMiembro.html");
  }


}
