package ddsutn.controllers;

import ddsutn.domain.organization.Organization;
import ddsutn.domain.organization.Sector;
import ddsutn.domain.organization.workApplication.WorkApplication;
import ddsutn.dtos.organization.OrganizationForView;
import ddsutn.dtos.organization.SectorForView;
import ddsutn.security.user.StandardUser;
import ddsutn.security.user.User;
import ddsutn.services.MemberSvc;
import ddsutn.services.OrganizationSvc;
import ddsutn.services.UserSvc;
import ddsutn.services.WorkApplicationSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/organizaciones")
public class OrganizationController {

  private final String organizationsHtml = "/organizations/organizations";
  private final String organizationDetailsHtml = "/organizations/organizationDetails";
  private final String organizationApplicationsHtml = "/organizations/workApplications";


  @Autowired
  private OrganizationSvc organizationSvc;

  @Autowired
  private UserSvc userSvc;

  @Autowired
  private MemberSvc memberSvc;

  @Autowired
  private WorkApplicationSvc workApplicationSvc;

  @GetMapping("")
  public ModelAndView showOrganizations(@RequestParam(defaultValue = "") String like) {
    ModelAndView mav = new ModelAndView();
    mav.addObject("allOrganizations", this.organizationsList(like));
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
    Collection<? extends GrantedAuthority> authorities =
            SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    Boolean isStandardUser = authorities.stream().anyMatch(ga -> ga.getAuthority().equals("STANDARD_USER"));

    if (org == null) {
      mav.setStatus(HttpStatus.NOT_FOUND);
      return mav;
    }

    OrganizationForView ofv = new OrganizationForView(org);
    mav.addObject("org", ofv);
    mav.addObject("isStandardUser", isStandardUser);
    mav.addObject("allOrgSectors", this.getSectorsForOrg(org));
    return mav;
  }

  @PostMapping("/{org_id}/sectors/{sector_id}/aplicaciones")
  public ModelAndView applyToSector(@PathVariable Long org_id, @PathVariable Long sector_id) {
    ModelAndView mav = new ModelAndView();
    Organization org = organizationSvc.findById(org_id);
    Sector sector = org.getSectors().stream().filter(s -> s.getId().equals(sector_id)).findFirst().orElse(null);
    Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

    if (authorities.stream().map(GrantedAuthority::getAuthority).noneMatch(auth -> auth.equals("STANDARD_USER"))) {
      mav.setStatus(HttpStatus.FORBIDDEN);
      return mav;
    } else if (sector == null) {
      mav.setStatus(HttpStatus.NOT_FOUND);
      return mav;
    }

    String name = SecurityContextHolder.getContext().getAuthentication().getName();
    StandardUser myUser = (StandardUser) userSvc.loadUserByUsername(name);

    myUser.getMember().applyToSector(sector);
    organizationSvc.save(org);
    mav.setViewName("redirect:/organizaciones/" + org_id.toString());
    return mav;
  }

  @GetMapping("/{id}/solicitudes")
  public ModelAndView showWorkApplications(@PathVariable Long id) {
    ModelAndView mav = new ModelAndView();
    Organization org = organizationSvc.findById(id);

    Set<SectorForView> sfvs = org.getSectors()
            .stream()
            .filter(sector -> sector.hasPendingApplications())
            .map(SectorForView::new)
            .collect(Collectors.toSet());
    mav.addObject("organization", new OrganizationForView(org));
    mav.addObject("allSectorsWithApplications", sfvs);
    mav.setViewName(organizationApplicationsHtml);
    return mav;
  }

  @PostMapping("/{org_id}/solicitudes/{wa_id}")
  public ModelAndView resolveApplication(
          @PathVariable Long org_id,
          @PathVariable String wa_id,
          @RequestParam String accept,
          @RequestParam String reject) {
    ModelAndView mav = new ModelAndView();
    Boolean acceptCond = Boolean.parseBoolean(accept);
    Boolean rejectCond = Boolean.parseBoolean(reject);

    Organization org = organizationSvc.findById(org_id);

    WorkApplication workApplication = workApplicationSvc.findAllByCondition(wa -> wa.getId().equals(wa_id))
            .stream()
            .findFirst()
            .orElse(null);

    if (workApplication == null || org == null) {
      mav.setStatus(HttpStatus.NOT_FOUND);
      return mav;
    }

    if (acceptCond == rejectCond) {
      mav.setStatus(HttpStatus.BAD_REQUEST);
      return mav;
    }

    mav.setViewName("redirect:/organizaciones/" + org_id + "/solicitudes");

    if (acceptCond) {
      workApplication.accept();
    }

    if (rejectCond) {
      workApplication.reject();
    }

    organizationSvc.save(org);
    workApplicationSvc.save(workApplication);

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

  private List<OrganizationForView> organizationsList(String like) {
    List<Organization> allSavedOrgs = this.organizationSvc
            .findAllByCondition(org -> org.getSocialObjective().toUpperCase().contains(like.toUpperCase()));
    return allSavedOrgs.stream().map(OrganizationForView::new).collect(Collectors.toList());
  }

  private List<SectorForView> getSectorsForOrg(Organization org) {
    return org.getSectors().stream().map(SectorForView::new).collect(Collectors.toList());
  }

}
