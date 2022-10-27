package ddsutn.controllers;

import ddsutn.security.user.StandardUser;
import ddsutn.security.user.User;
import ddsutn.services.UserSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

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
		model.addAttribute("newStandardUser", new StandardUser());
		return "registrarse";
	}

	@PostMapping("/registrarse")
	public String signUp(@ModelAttribute("newStandardUser") StandardUser user) {
		this.userSvc.save(user);
		return "iniciarSesion"; //TODO ver como crear admins, los miembros asociados y toda la wea
	}

}
