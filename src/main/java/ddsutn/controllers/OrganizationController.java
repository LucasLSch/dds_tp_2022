package ddsutn.controllers;

import ddsutn.domain.organization.Organization;
import ddsutn.domain.organization.Sector;
import ddsutn.services.OrganizationSvc;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
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
    Organization org = this.organizationSvc.findById(id);
    //TODO ver como devolver mensajes de error con codigo 404
    OrganizationForView ofv = this.transformOrgForView(org);
    model.addAttribute("org", ofv);
    model.addAttribute("allOrgSectors", this.getSectorsForOrg(org));
    return "detalleOrganizacion";
  }

//  @GetMapping("/{id}/solicitar")
//  public String apply(@PathVariable Long id, Model model) {
//    return "simular que solicita para org " + id;
//  }
//
//  @GetMapping("/{id}/aceptar")
//  public String accept(@PathVariable Long id, Model model) {//cirtcular view path, que esta pasando
//    return "aceptarMiembro";
//  }


  // --- Utils --- //

  private List<OrganizationForView> organizationsList() {
    List<Organization> allSavedOrgs = this.organizationSvc.findAll();
    return allSavedOrgs.stream().map(this::transformOrgForView).collect(Collectors.toList());
  }

  private List<SectorForView> getSectorsForOrg(Organization org) {
    return org.getSectors().stream().map(this::transformSectorForView).collect(Collectors.toList());
  }

  private OrganizationForView transformOrgForView(Organization org) {
    OrganizationForView ofv = new OrganizationForView();
    ofv.setId(org.getId());
    ofv.setSocialObj(org.getSocialObjective());
    ofv.setLocation(org.getLocation().getStreet() + " " + org.getLocation().getHeight());
    ofv.setMembersAmount(org.getMembers().size());
    ofv.setSectors(org.getSectors().stream().map(Sector::getName).collect(Collectors.toList()));
//    ofv.setPhoneNumber(org.getContacts().get(0).getPhoneNumber());
//    ofv.setPhoneNumber(org.getContacts().get(0).getEmail());
    return ofv;
  }

  private SectorForView transformSectorForView(Sector sector) {
    SectorForView sfv = new SectorForView();
    sfv.setId(sector.getId());
    sfv.setName(sector.getName());
    sfv.setMembersAmount(sector.membersAmount());
    return sfv;
  }

  @Setter
  public class OrganizationForView {
    public Long id;
    public String socialObj;
    public String location;
    public Integer membersAmount;
    public List<String> sectors;
    public String phoneNumber;
    public String email;
  }

  @Setter
  public class SectorForView {
    public Long id;
    public String name;
    public Integer membersAmount;
  }

}
