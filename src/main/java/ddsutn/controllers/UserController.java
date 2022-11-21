package ddsutn.controllers;

import ddsutn.domain.organization.DocType;
import ddsutn.domain.organization.Member;
import ddsutn.domain.organization.Organization;
import ddsutn.domain.territories.TerritorialSectorAgent;
import ddsutn.security.passwordvalidator.PasswordException;
import ddsutn.security.passwordvalidator.PasswordValidator;
<<<<<<< HEAD
import ddsutn.security.user.*;
=======
import ddsutn.security.user.TerritorialAgentUser;
import ddsutn.security.user.User;
>>>>>>> c1c6f8faaa03dcc6e89ace82f8d0966f2eb34aa6
import ddsutn.services.UserSvc;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.security.auth.login.LoginException;
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

  @GetMapping("/iniciarSesion")
  public String logIn(Model model) {
    model.addAttribute("user", new UserInit());
    return loginHtml;
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
      return loginHtml;
    }
    return "/home";
  }

  @GetMapping("/registrarse")
  public String signUp(Model model) {
    model.addAttribute("newUser", new UserInit());
    model.addAttribute("allUserTypes", this.userTypes());
    model.addAttribute("pwdError", "");
    model.addAttribute("userError", "");
    return registerHtml;
  }

  @PostMapping("/registrarse")
  public String signUp(@ModelAttribute("newUser") UserInit user, Model model) {

    if (this.validateNewUser(user, model)) {
      model.addAttribute("allUserTypes", this.userTypes());
      return registerHtml;
    }

    switch (user.userType) {
      case "Agente Territorial":
        return this.registerAgentUser(user);
      case "Miembro":
        return this.registerMember(user, model);
      case "Administrador de Organización":
        return this.registerOrgAdmin(user, model); //Repensar cuenta de org, no la tenemos xd
      default:
        return registerHtml;
    }
  }

  @PostMapping(value = "/registrarseMiembro")
  public String signUp(@ModelAttribute("user") MemberForView newMember, Model model) {
    System.out.println(newMember.username);
    System.out.println(newMember.password);
    System.out.println(newMember.name);
//    DocType docTypeSelected = DocType.valueOf(newMember.docType.replace('/', '_'));
//    try {
//      StandardUser newUser = new StandardUser(
//          newMember.username,
//          newMember.password, new Member(
//            newMember.name,
//            newMember.surname,
//            docTypeSelected,
//            newMember.docNumber));
//      this.userSvc.save(newUser);
//      return loginHtml;
//    } catch (IOException e) {
//      return registerHtml;
//    }
    return registerHtml;
  }

  private Boolean validateNewUser(UserInit newUser, Model model) {

    Boolean hasError = false;

    if (!this.userSvc.findAllByCondition(u -> u.getUsername().equals(newUser.username)).isEmpty()) {
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

  private String registerMember(UserInit userInit, Model model) {
      MemberForView newMember = new MemberForView();
      newMember.setUsername(userInit.username);
      newMember.setPassword(userInit.password);
      model.addAttribute("newMember", newMember);
      model.addAttribute("allDocTypes", this.docTypes());
      return registerMemberHtml;
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
  public class UserInit {
    public String username;
    public String password;
    public String userType;
  }

  @Setter
  @Getter
  public class MemberForView {
    public String username;
    public String password;
    public String name;
    public String surname;
    public String docType;
    public String docNumber;
  }

}
