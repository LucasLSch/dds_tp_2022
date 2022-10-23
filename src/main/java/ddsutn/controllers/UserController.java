package ddsutn.controllers;

import ddsutn.domain.measurements.ActivityData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
@RestController
public class UserController {
	@GetMapping("/iniciarSesion")
	public ModelAndView logIn() {
		return new ModelAndView("IniciarSesion.html");
	}

	@GetMapping("/Registrarse")
	public ModelAndView signUp() {
		return new ModelAndView("Registrarse.html");
	}
}
