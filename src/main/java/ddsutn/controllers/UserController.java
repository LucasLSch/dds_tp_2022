package ddsutn.controllers;

import ddsutn.domain.organization.DocType;
import ddsutn.domain.organization.Member;
import ddsutn.domain.territories.TerritorialSectorAgent;
import ddsutn.security.passwordvalidator.PasswordException;
import ddsutn.security.passwordvalidator.PasswordValidator;
import ddsutn.security.user.Administrator;
import ddsutn.security.user.StandardUser;
import ddsutn.security.user.TerritorialAgentUser;
import ddsutn.security.user.User;
import ddsutn.services.MemberSvc;
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
      User successfulUser =
              userSvc.findAllByCondition((someUser) -> someUser.successfulLogin(user.getUsername(),
                              bCryptPasswordEncoder.encode(user.getPassword())))
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

  @PostMapping("/registrarseMiembro")
  public ModelAndView getMemberInfo(
          @ModelAttribute("newMember") StandardUserDTO member,
          @ModelAttribute("newUser") UserInit user,
          Model model
  ) throws IOException {
    Member m = member.getMember();
    StandardUser su = user.getUser(m, bCryptPasswordEncoder);
    userSvc.save(su);
    return new ModelAndView(String.format("/member/%d", m.getId()), "member", m);
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
  public static class UserInit {
    public String username;
    public String password;
    public String userType;

    public StandardUser getUser(Member member, BCryptPasswordEncoder encoder) throws IOException {
      return new StandardUser(username, encoder.encode(password), member);
    }
  }

  @Setter
  @Getter
  public static class StandardUserDTO {
    public String name;
    public String surname;
    public String docType;
    public String document;

    public Member getMember() {
      return new Member(name, surname, DocType.valueOf(docType), document);
    }
  }

}
