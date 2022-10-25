package ddsutn.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@GetMapping("/iniciarSesion")
	public String logIn(Model model) {
		return "iniciarSesion";
	}

	@GetMapping("/Registrarse")
	public String signUp(Model modle) {
		return "registrarse";
	}

}
