package ddsutn.controllers;

import ddsutn.domain.organization.Organization;
import ddsutn.dtos.organization.OrganizationForView;
import ddsutn.dtos.organization.SectorForView;
import ddsutn.services.OrganizationSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/organizaciones")
public class OrganizationController {

  private final String organizationsHtml = "/organizations/organizations";
  private final String organizationDetailsHtml = "/organizations/organizationDetails";


  @Autowired
  private OrganizationSvc organizationSvc;

  @GetMapping("")
  public ModelAndView showOrganizations() {
    ModelAndView mav = new ModelAndView();
    mav.addObject("allOrganizations", this.organizationsList());
    mav.setViewName(organizationsHtml);
    return mav;
  }

  // TODO ver que hacer con esto
//  @GetMapping("/search")
//  public String searchOrganizations(Model model) {
//    OrganizationSvc svc = new OrganizationSvc();
//    model.addAttribute("organizations", svc.findAll());
//    return "buscarOrganizacion";
//  }
//
//  @RequestMapping("/search/")//refrescarme como hacer un search
//  public String searchOrganizacion(@RequestParam(value = "nombre", required = true) String param,
//                                   Model model) {
//    OrganizationSvc svc = new OrganizationSvc();
//    model.addAttribute("organizations",
//        svc.findAllByCondition((organization) -> organization.getSocialObjective().equals(param))
//    );
//    return "buscarOrganizacion";
//  }

  @GetMapping("/{id}")
  public ModelAndView showOrganizatinoById(@PathVariable Long id) {
    ModelAndView mav = new ModelAndView(organizationDetailsHtml);
    Organization org = this.organizationSvc.findById(id);

    if (org == null) {
      mav.setStatus(HttpStatus.NOT_FOUND);
      return mav;
    }

    OrganizationForView ofv = new OrganizationForView(org);
    mav.addObject("org", ofv);
    mav.addObject("allOrgSectors", this.getSectorsForOrg(org));
    return mav;
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
    return allSavedOrgs.stream().map(OrganizationForView::new).collect(Collectors.toList());
  }

  private List<SectorForView> getSectorsForOrg(Organization org) {
    return org.getSectors().stream().map(SectorForView::new).collect(Collectors.toList());
  }

}
