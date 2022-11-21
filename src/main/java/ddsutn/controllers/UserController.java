package ddsutn.controllers;

import ddsutn.domain.territories.TerritorialSectorAgent;
import ddsutn.security.passwordvalidator.PasswordException;
import ddsutn.security.passwordvalidator.PasswordValidator;
import ddsutn.security.user.TerritorialAgentUser;
import ddsutn.security.user.User;
import ddsutn.services.UserSvc;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {

  @Autowired
  private UserSvc userSvc;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @GetMapping("/iniciarSesion")
  public String logIn(Model model) {
    model.addAttribute("user", new UserInit());
    return "iniciarSesion";
  }

  @PostMapping("/iniciarSesion")
  public String logIn(@ModelAttribute("user") UserInit user, Model model) {
    try {
      User succesfulUser =
              userSvc.findAllByCondition((someUser) -> someUser.successfulLogin(user.getUsername(), user.getPassword()))
                      .stream()
                      .findFirst()
                      .get();
    } catch (Exception e) {
      return "/iniciarSesion";
    }
    return "/home";
  }

  @GetMapping("/registrarse")
  public String signUp(Model model) {
    model.addAttribute("newUser", new UserInit());
    model.addAttribute("allUserTypes", this.userTypes());
    model.addAttribute("pwdError", "");
    model.addAttribute("userError", "");
    return "registrarse/registrarse";
  }

  @PostMapping("/registrarse")
  public ModelAndView signUp(@ModelAttribute("newUser") UserInit user, Model model) {

    if(this.validateNewUser(user, model)) {
      model.addAttribute("allUserTypes", this.userTypes());
      return new ModelAndView("registrarse/registrarse", "newUser", user);
    }

    switch (user.getUserType()) {
      case "Miembro":
        return new ModelAndView("registrarse/registrarseMiembro", "newUser", user);
      default:
        return new ModelAndView("registrarse/registrarse");
    }

  }

  private Boolean validateNewUser(UserInit newUser, Model model) {

    Boolean hasError = false;

    if(!this.userSvc.findAllByCondition(u -> u.getUsername().equals(newUser.username)).isEmpty()) {
      model.addAttribute("userError", "Usuario ya ocupado!");
      hasError = true;
    }

    try {
      new PasswordValidator().validatePassowrd(newUser.password);
    } catch (IOException ioe) {
      model.addAttribute("pwdError", "Ups! Prueba de nuevo");
      hasError = true;
    } catch (PasswordException pe) {
      model.addAttribute("pwdError", pe.getMessage());
      hasError = true;
    }

    return hasError;
  }

  private String saveAgentUser(UserInit user) {
    try {
      TerritorialAgentUser newUser = new TerritorialAgentUser(
          user.username,
          user.password,
          new TerritorialSectorAgent());

      this.userSvc.save(newUser);
      return "iniciarSesion";

    } catch (IOException e) {
      return "registrarse/registrarse";
    }
  }

  private List<String> userTypes() {
    return Arrays.asList("-- Seleccione Una Opción --", "Miembro", "Organización", "Agente Territorial");
  }

  @Setter
  @Getter
  public class UserInit {
    public String username;
    public String password;
    public String userType;
  }

}
