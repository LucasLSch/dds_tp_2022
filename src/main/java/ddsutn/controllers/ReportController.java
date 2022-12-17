package ddsutn.controllers;

import ddsutn.domain.measurements.CarbonFootprint;
import ddsutn.security.user.OrgAdminUser;
import ddsutn.security.user.StandardUser;
import ddsutn.security.user.TerritorialAgentUser;
import ddsutn.security.user.User;
import ddsutn.services.ReporterSvc;
import ddsutn.services.UserSvc;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

@RestController
public class ReportController {

  private final String DIRECTORY_PATH = "src/main/resources/static";
  private final String RESOURCE_PATH = "/temp/reports/";
  private final String BASE_FILE_NAME = "ReporteHuellasDeCarbono";
  private final String EXTENSION = ".json";

  @Autowired
  private ReporterSvc reporterSvc;

  @Autowired
  private UserSvc userSvc;

  @GetMapping("/huellas")
  public ModelAndView showReports(@RequestParam(value = "rt", required = false, defaultValue = "") String rt) {
    ModelAndView mav = new ModelAndView("reports/reports");
    String role = getUserRole();
    mav.addObject("role", role);
    mav.addObject("allReportTypes", this.reportTypesFor(role));
    mav.addObject("electedType", rt);


    if (rt.isEmpty() || rt.equals("0")) {
      return mav;
    }

    Long id = getProperId(role);
    Long userId = getUserId();
    String report = this.getReportFor(role, rt, id);

    String fileName = BASE_FILE_NAME + rt + "_uid_" + userId + "_" + LocalDate.now();
    String finalPath = RESOURCE_PATH + fileName + EXTENSION;

    mav.addObject("report", report);
    mav.addObject("filePath", finalPath);
    mav.addObject("fileName", fileName);

    this.createReportsDirectory();
    this.createReportFile(DIRECTORY_PATH + RESOURCE_PATH + fileName + EXTENSION, report);

    return mav;
  }

  @GetMapping("/temp/reports/{file}")
  public InputStreamResource FileSystemResource (HttpServletResponse response, @PathVariable String file) throws IOException {
    response.setContentType("application/json");
    response.setHeader("Content-Disposition", "attachment;filename=" + file);
    return new InputStreamResource(new FileInputStream(DIRECTORY_PATH + RESOURCE_PATH + file));
  }

  @PostMapping("/huellas")
  public ModelAndView deleteReport(@RequestParam(value = "fp") String fp,
                                   @RequestParam(value = "rt") String rt) {
    ModelAndView mav = new ModelAndView("redirect:/huellas");

    Long userId = this.getUserId();

    System.out.println(fp.length());

    String userSubstring = fp.substring(
        RESOURCE_PATH.length() + BASE_FILE_NAME.length() + rt.length(),
        (fp.length()) - EXTENSION.length() - LocalDate.now().toString().length() - 1);

    System.out.println(userSubstring);

    if (userSubstring.matches("_uid_" + userId)) {
      System.out.println("Path verifies user");
      File myObj = new File(DIRECTORY_PATH + fp);
      if (myObj.delete()) {
        System.out.println("Deleted the file: " + myObj.getName());
      } else {
        System.out.println("Failed to delete the file.");
      }
    } else {
      System.out.println("Path does not verify user");

    }

    return mav;
  }

  private List<ReportType> reportTypesFor(String role) {

    List<ReportType> allTypes = new ArrayList<>();

    switch (role) {
      case "STANDARD_USER":
        allTypes.addAll(Arrays.asList(
            new ReportType("Histórico", "Histórico"),
            new ReportType("Por trayecto", "PorTrayecto")));
        break;
      case "ORG_ADMIN_USER":
        allTypes.addAll(Arrays.asList(
            new ReportType("Histórico", "Histórico"),
            new ReportType("Por sector", "PorSector")));
        break;
      case "AGENT_USER":
        allTypes.addAll(Arrays.asList(
            new ReportType("Histórico", "Histórico"),
            new ReportType("Por organización", "PorOrganización")));
        break;
      case "ADMINISTRATOR_USER":
        allTypes.addAll(Arrays.asList(
            new ReportType("Por sector territorial", "PorSectorTerritorial"),
            new ReportType("Por tipo de organización", "PorTipoDeOrganización")));
        break;
      default:
        return Collections.emptyList();
    }

    return allTypes;
  }

  private String getReportFor(String role, String rt, Long id) {
    switch (role) {
      case "ADMINISTRATOR_USER":
        if (rt.equals("PorSectorTerritorial"))
          return this.reporterSvc.territorialSectorsCfReport(CarbonFootprint.getDefaultUnit()).toString();
        if (rt.equals("PorTipoDeOrganización"))
          return this.reporterSvc.organizationTypeCfReport(CarbonFootprint.getDefaultUnit()).toString();
      case "STANDARD_USER":
        if (rt.equals("Histórico"))
          return this.reporterSvc.memberHistoricalCFReport(CarbonFootprint.getDefaultUnit(), id).toString();
        if (rt.equals("PorTrayecto"))
          return this.reporterSvc.memberJourneyCFReport(CarbonFootprint.getDefaultUnit(), id).toString();
      case "ORG_ADMIN_USER":
        if (rt.equals("Histórico"))
          return this.reporterSvc.organizationHistoricalCFReport(CarbonFootprint.getDefaultUnit(), id).toString();
        if (rt.equals("PorSector"))
          return this.reporterSvc.organizationSectorCFReport(CarbonFootprint.getDefaultUnit(), id).toString();
      case "AGENT_USER":
        if (rt.equals("Histórico"))
          return this.reporterSvc.territorialSectorHistoricalCFReport(CarbonFootprint.getDefaultUnit(), id).toString();
        if (rt.equals("PorOrganización"))
          return this.reporterSvc.territorialSectorOrganizationCFReport(CarbonFootprint.getDefaultUnit(), id).toString();
      default:
        return "";
    }
  }

  private Long getUserId() {
    String name = SecurityContextHolder.getContext().getAuthentication().getName();
    User myUser = userSvc.loadUserByUsername(name);
    return myUser.getId();
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

  private void createReportsDirectory() {
    try {
      Files.createDirectories(Paths.get(DIRECTORY_PATH));
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  private void createReportFile(String path, String content) {
    try {
      FileWriter myWriter = new FileWriter(path);
      myWriter.write(content);
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
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
