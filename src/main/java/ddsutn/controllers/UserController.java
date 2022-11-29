package ddsutn.controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import ddsutn.domain.organization.DocType;
import ddsutn.domain.organization.Member;
import ddsutn.domain.territories.TerritorialSectorAgent;
import ddsutn.security.passwordvalidator.PasswordException;
import ddsutn.security.passwordvalidator.PasswordValidator;
import ddsutn.security.user.StandardUser;
import ddsutn.security.user.TerritorialAgentUser;
import ddsutn.security.user.User;
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

@Controller
public class UserController {

  private final String loginHtml = "iniciarSesion";
  private final String registerHtml = "registrarse/registrarse";
  private final String registerMemberHtml = "registrarse/registrarseMiembro";
  private final String registerOrgHtml = "registrarse/registrarseOrg";

  @Autowired
  private UserSvc userSvc;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @GetMapping("/iniciarSesion")
  public ModelAndView logIn() {
    ModelAndView mav = new ModelAndView(loginHtml);
    mav.addObject("user", new UserInit());
    return mav;
  }

  @PostMapping("/iniciarSesion")
  public String logIn(@ModelAttribute("user") UserInit user, Model model) {
    try {
      User successfulUser =
              userSvc.findAllByCondition((someUser) -> someUser.successfulLogin(user.getUsername(),
                              bCryptPasswordEncoder.encode(user.getPassword())))
                      .stream()
                      .findFirst()
                      .get();
    } catch (Exception e) {
      return loginHtml;
    }
    return "home";
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
    username = username.substring(0, username.length() - 1);
    password = password.substring(0, password.length() - 1);
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

  private String registerOrgAdmin(UserInit userInit, Model model) {
    //TODO
    return registerHtml;
  }

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

}
