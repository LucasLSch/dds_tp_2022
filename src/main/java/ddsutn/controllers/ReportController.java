package ddsutn.controllers;

import ddsutn.domain.measurements.CarbonFootprint;
import ddsutn.security.user.OrgAdminUser;
import ddsutn.security.user.StandardUser;
import ddsutn.security.user.TerritorialAgentUser;
import ddsutn.services.ReporterSvc;
import ddsutn.services.UserSvc;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
public class ReportController {

  @Autowired
  private ReporterSvc reporterSvc;

  @Autowired
  private UserSvc userSvc;

  @GetMapping("/huellas")
  public ModelAndView showReports(@RequestParam(value = "rt", required = false, defaultValue = "1") String rt) {
    ModelAndView mav = new ModelAndView("reports/reports");
    String role = getUserRole();
    mav.addObject("role", role);
    mav.addObject("allReportTypes", this.reportTypesFor(role));

    if (rt.equals("")) {
      return mav;
    }

    Long id = getProperId(role);
    String report = this.getReportFor(role, rt, id);
    mav.addObject("report", report);

    return mav;
  }

  private List<ReportType> reportTypesFor(String role) {
    switch (role) {
      case "STANDARD_USER":
        return Arrays.asList(
            new ReportType("Histórico", "1"),
            new ReportType("Por trayecto", "2"));
      case "ORG_ADMIN_USER":
        return Arrays.asList(
            new ReportType("Histórico", "1"),
            new ReportType("Por sector", "2"));
      case "AGENT_USER":
        return Arrays.asList(
            new ReportType("Histórico", "1"),
            new ReportType("Por organización", "2"));
      case "ADMINISTRATOR_USER":
        return Arrays.asList(
            new ReportType("Por sector territorial", "1"),
            new ReportType("Por tipo de organización", "2"));
      default:
        return Collections.emptyList();
    }
  }

  private String getReportFor(String role, String rt, Long id) {
    switch (role) {
      case "ADMINISTRATOR_USER":
        if (rt.equals("1"))
          return this.reporterSvc.territorialSectorsCfReport(CarbonFootprint.getDefaultUnit()).toString();
        if (rt.equals("2"))
          return this.reporterSvc.organizationTypeCfReport(CarbonFootprint.getDefaultUnit()).toString();
      case "STANDARD_USER":
        if (rt.equals("1"))
          return this.reporterSvc.memberHistoricalCFReport(CarbonFootprint.getDefaultUnit(), id).toString();
        if (rt.equals("2"))
          return this.reporterSvc.memberJourneyCFReport(CarbonFootprint.getDefaultUnit(), id).toString();
      case "ORG_ADMIN_USER":
        if (rt.equals("1"))
          return this.reporterSvc.organizationHistoricalCFReport(CarbonFootprint.getDefaultUnit(), id).toString();
        if (rt.equals("2"))
          return this.reporterSvc.organizationSectorCFReport(CarbonFootprint.getDefaultUnit(), id).toString();
      case "AGENT_USER":
        if (rt.equals("1"))
          return this.reporterSvc.territorialSectorHistoricalCFReport(CarbonFootprint.getDefaultUnit(), id).toString();
        if (rt.equals("2"))
          return this.reporterSvc.territorialSectorOrganizationCFReport(CarbonFootprint.getDefaultUnit(), id).toString();
      default:
        return "";
    }
  }


  private String getUserRole() {
    Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    return authorities.stream().findFirst().get().getAuthority();
  }

  private Long getProperId(String role) {
    String name = SecurityContextHolder.getContext().getAuthentication().getName();
    switch (role) {
      case "STANDARD_USER":
        StandardUser standardUser = (StandardUser) userSvc.loadUserByUsername(name);
        return standardUser.getMember().getId();
      case "ORG_ADMIN_USER":
        OrgAdminUser orgAdmin = (OrgAdminUser) userSvc.loadUserByUsername(name);
        return orgAdmin.getOrganization().getId();
      case "AGENT_USER":
        TerritorialAgentUser taUser = (TerritorialAgentUser) userSvc.loadUserByUsername(name);
        return taUser.getTerritorialSectorAgent().getTerritorialSector().getId();
      default:
        return 0L;
    }
  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public class ReportType {
    public String name;
    public String value;
  }

}
