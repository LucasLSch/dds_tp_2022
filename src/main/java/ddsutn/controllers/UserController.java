package ddsutn.controllers;

import ddsutn.domain.organization.DocType;
import ddsutn.domain.organization.Member;
import ddsutn.domain.organization.Organization;
import ddsutn.domain.organization.Sector;
import ddsutn.domain.territories.TerritorialSectorAgent;
import ddsutn.security.passwordvalidator.PasswordException;
import ddsutn.security.passwordvalidator.PasswordValidator;
import ddsutn.security.user.StandardUser;
import ddsutn.security.user.TerritorialAgentUser;
import ddsutn.security.user.User;
import ddsutn.services.OrganizationSvc;
import ddsutn.services.UserSvc;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

  private final String loginHtml = "iniciarSesion";
  private final String registerHtml = "registrarse/registrarse";
  private final String registerMemberHtml = "registrarse/registrarseMiembro";
  private final String registerOrgAdminHtml = "registrarse/registrarseOrgAdmin";

  @Autowired
  private UserSvc userSvc;

  @Autowired
  private OrganizationSvc organizationSvc;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @GetMapping("/iniciarSesion")
  public ModelAndView logIn() {
    ModelAndView mav = new ModelAndView(loginHtml);
    mav.addObject("user", new UserInit());
    return mav;
  }

  @PostMapping("/iniciarSesion")
  public ModelAndView logIn(@ModelAttribute("user") UserInit user) {
    ModelAndView mav = new ModelAndView();
    try {
      User successfulUser =
              userSvc.findAllByCondition((someUser) -> someUser.successfulLogin(user.getUsername(),
                              bCryptPasswordEncoder.encode(user.getPassword())))
                      .stream()
                      .findFirst()
                      .get();
    } catch (Exception e) {
      mav.setViewName(loginHtml);
      return mav;
    }
    mav.setViewName("redirect:/");
    return mav;
  }

  @GetMapping("/registrarse")
  public ModelAndView signUp() {
    ModelAndView modelAndView = new ModelAndView(registerHtml);
    modelAndView.addObject("newUser", new UserInit());
    modelAndView.addObject("allUserTypes", this.userTypes());
    return modelAndView;
  }

  @PostMapping("/registrarse")
  public ModelAndView signUp(@ModelAttribute("newUser") UserInit user) {
    ModelAndView mav = new ModelAndView();

    if (this.validateNewUser(user, mav)) {
      mav.addObject("allUserTypes", this.userTypes());
      mav.setViewName(registerHtml);
      return mav;
    }

    switch (user.getUserType()) {
      case "-- Seleccione Una Opción --":
        return this.signUp();
      case "Miembro":
        return this.registerMember(user, mav);
      default:
        return new ModelAndView("registrarse/registrarse");
    }

  }

  @PostMapping("/registrarseMiembro")
  public ModelAndView getMemberInfo(
          @ModelAttribute("newMember") StandardUserDTO member,
          @RequestParam String username,
          @RequestParam String password
  ) throws IOException {
    member.setUsername(username);
    member.setPassword(password);
    StandardUser su = member.getUser();
    userSvc.save(su);
    return this.logIn();
  }

  private Boolean validateNewUser(UserInit newUser, ModelAndView mav) {

    Boolean hasError = false;

    if (!this.userSvc.findAllByCondition(u -> u.getUsername().equals(newUser.username)).isEmpty()) {
      mav.addObject("userError", "Usuario ya ocupado!");
      hasError = true;
    }

    if (newUser.username.isEmpty()) {
      mav.addObject("userError", "Ingrese un usuario!");
      hasError = true;
    }

    try {
      new PasswordValidator().validatePassowrd(newUser.password);
    } catch (IOException ioe) {
      mav.addObject("pwdError", "Ups! Prueba de nuevo");
      hasError = true;
    } catch (PasswordException pe) {
      mav.addObject("pwdError", pe.getMessage());
      hasError = true;
    }

    return hasError;
  }

  private ModelAndView registerMember(UserInit userInit, ModelAndView mav) {
    StandardUserDTO userDTO = new StandardUserDTO();
    String username = userInit.getUsername();
    String password = bCryptPasswordEncoder.encode(userInit.getPassword());
    String htmlParams = "?" + "username=" + username + "&" + "password=" + password;
    mav.addObject("path", "registrarseMiembro" + htmlParams);
    mav.addObject("newMember", userDTO);
    mav.addObject("allDocTypes", this.docTypes());
    mav.setViewName(registerMemberHtml);
    return mav;
  }
/*
  private ModelAndView registerOrgAdmin(UserInit userInit, ModelAndView mav) {
    OrgAdminDTO userDTO = new OrgAdminDTO();
    String username = userInit.getUsername();
    String password = bCryptPasswordEncoder.encode(userInit.getPassword());
    String htmlParams = "?" + "username=" + username + "&" + "password=" + password;
    mav.addObject("path", "registrarseMiembro" + htmlParams);
    mav.addObject("newOrgAdmin", userDTO);
    mav.addObject("allOrganizations", this.organizationsList());
    mav.setViewName(registerOrgAdminHtml);
    return mav;
  }
*/
  private String registerAgentUser(UserInit user) {
    try {
      TerritorialAgentUser newUser = new TerritorialAgentUser(
              user.username,
              user.password,
              new TerritorialSectorAgent());
      this.userSvc.save(newUser);
      return loginHtml;
    } catch (IOException e) {
      return registerHtml;
    }
  }

  private List<String> docTypes() {
    return Arrays.asList("-- Seleccione Una Opción --", "DNI", "PASAPORTE", "CUIL/CUIT");
  }

  private List<String> userTypes() {
    return Arrays.asList("-- Seleccione Una Opción --", "Miembro", "Administrador de Organización", "Agente Territorial");
  }
/*
  private OrganizationForView transformOrgForView(Organization org) {
    //TODO: extraer logica repetida
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

  private List<OrganizationForView> organizationsList() {
    List<Organization> allSavedOrgs = this.organizationSvc.findAll();
    return allSavedOrgs.stream().map(this::transformOrgForView).collect(Collectors.toList());
  }
*/
  @Setter
  @Getter
  public static class UserInit {
    public String username;
    public String password;
    public String userType;
  }

  @Setter
  @Getter
  public static class StandardUserDTO {
    public String username;
    public String password;
    public String name;
    public String surname;
    public String docType;
    public String docNumber;

    public StandardUser getUser() throws IOException {
      return new StandardUser(username, password, this.getMember());
    }

    public Member getMember() {
      return new Member(name, surname, DocType.valueOf(docType), docNumber);
    }
  }
/*
  @Setter
  @Getter
  public static class OrgAdminDTO {
    public String username;
    public String password;
    public String organizationId;
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
*/
}
