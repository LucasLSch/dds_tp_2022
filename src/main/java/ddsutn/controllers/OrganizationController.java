package ddsutn.controllers;

import ddsutn.domain.organization.Organization;
import ddsutn.domain.organization.Sector;
import ddsutn.services.OrganizationSvc;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/organizaciones")
public class OrganizationController {

  @Autowired
  private OrganizationSvc organizationSvc;

  @GetMapping("")
  public String showOrganizations(Model model) {
    model.addAttribute("allOrganizations", this.organizationsList());
    return "organizaciones";
  }

  @GetMapping("/search")
  public String searchOrganizations(Model model) {
    OrganizationSvc svc = new OrganizationSvc();
    model.addAttribute("organizations", svc.findAll());
    return "buscarOrganizacion";
  }

  @RequestMapping("/search/")//refrescarme como hacer un search
  public String searchOrganizacion(@RequestParam(value = "nombre", required = true) String param,
                                   Model model) {
    OrganizationSvc svc = new OrganizationSvc();
    model.addAttribute("organizations",
        svc.findAllByCondition((organization) -> organization.getSocialObjective().equals(param))
    );
    return "buscarOrganizacion";
  }

  @GetMapping("/{id}")
  public String showOrganizatinoById(@PathVariable Long id, Model model) {
    OrganizationSvc svc = new OrganizationSvc();
    Organization org = svc.findById(id);
    model.addAttribute("organization", org);
    Set<Sector> sectors = org.getSectors();
    model.addAttribute("sectors", sectors);
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


  // --- Utils --- //

  private List<OrganizationForView> organizationsList() {
    List<Organization> allSavedOrgs = this.organizationSvc.findAll();
    return allSavedOrgs.stream().map(this::transformOrgForView).collect(Collectors.toList());
  }

  private OrganizationForView transformOrgForView(Organization org) {
    OrganizationForView ofv = new OrganizationForView();
    ofv.setSocialObj(org.getSocialObjective());
    ofv.setLocation(org.getLocation().getStreet() + " " + org.getLocation().getHeight());
    ofv.setMembersAmount(org.getMembers().size());
    ofv.setSectors(org.getSectors().stream().map(Objects::toString).collect(Collectors.toList()));
    return ofv;
  }

  @Setter
  public class OrganizationForView {
    public String socialObj;
    public String location;
    public Integer membersAmount;
    public List<String> sectors;
  }

}
