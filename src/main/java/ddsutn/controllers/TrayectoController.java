package ddsutn.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/Trayecto")
public class TrayectoController {

	@GetMapping("")
	public String agregarTrayecto(Model model) {
		return "agregarTrayecto";
	}

}
