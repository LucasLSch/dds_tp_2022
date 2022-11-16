package ddsutn.controllers;

import ddsutn.domain.territories.TerritorialSectorAgent;
import ddsutn.security.passwordvalidator.PasswordException;
import ddsutn.security.passwordvalidator.PasswordValidator;
import ddsutn.security.user.TerritorialAgentUser;
import ddsutn.services.UserSvc;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {

  @Autowired
  private UserSvc userSvc;

  @GetMapping("/iniciarSesion")
  public String logIn(Model model) {
    return "iniciarSesion";
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
  public String signUp(@ModelAttribute("newUser") UserInit user, Model model) {

    if(this.validateNewUser(user, model)) {
      model.addAttribute("allUserTypes", this.userTypes());
      return "registrarse/registrarse";
    }

//    switch (user.userType) {
//      case "Agente Territorial":
//        return this.saveAgentUser(user);
//      case "Miembro":
//
//    }

    return "iniciarSesion";
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
