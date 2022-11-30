package ddsutn.controllers;

import ddsutn.dtos.user.StandardUserForView;
import ddsutn.dtos.user.UserForView;
import ddsutn.security.passwordvalidator.PasswordException;
import ddsutn.security.passwordvalidator.PasswordValidator;
import ddsutn.security.user.StandardUser;
import ddsutn.services.UserSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
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

  private final String loginHtml = "login/login";
  private final String registerHtml = "register/register";
  private final String registerMemberHtml = "register/registerMember";

  @Autowired
  private UserSvc userSvc;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @GetMapping("/iniciarSesion")
  public ModelAndView logIn() {
    ModelAndView mav = new ModelAndView(loginHtml);
    mav.addObject("user", new UserForView());
    return mav;
  }

  @PostMapping("/iniciarSesion")
  public ModelAndView logIn(@ModelAttribute("user") UserForView user) {
    ModelAndView mav = new ModelAndView();
    try {
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
    modelAndView.addObject("newUser", new UserForView());
    modelAndView.addObject("allUserTypes", this.userTypes());
    return modelAndView;
  }

  @PostMapping("/registrarse")
  public ModelAndView signUp(@ModelAttribute("newUser") UserForView user) {
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
        return new ModelAndView(registerHtml);
    }

  }

  @PostMapping("/registrarseMiembro")
  public ModelAndView getMemberInfo(
      @ModelAttribute("newMember") StandardUserForView member,
      @RequestParam String username,
      @RequestParam String password
  ) throws IOException {
    member.setUsername(username);
    member.setPassword(password);
    StandardUser su = member.getUser();
    userSvc.save(su);
    return this.logIn();
  }

  private Boolean validateNewUser(UserForView newUser, ModelAndView mav) {

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

  private ModelAndView registerMember(UserForView userInit, ModelAndView mav) {
    StandardUserForView userDTO = new StandardUserForView();
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
  */
  private List<String> docTypes() {
    return Arrays.asList("-- Seleccione Una Opción --", "DNI", "PASAPORTE", "CUIL/CUIT");
  }

  private List<String> userTypes() {
    return Arrays.asList("-- Seleccione Una Opción --", "Miembro", "Administrador de Organización", "Agente Territorial");
  }

}
