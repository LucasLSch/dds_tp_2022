package ddsutn.controllers;
import ddsutn.domain.measurements.ActivityData;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/Trayecto")
public class TrayectoController {
	@GetMapping("")
	public ModelAndView agregarTrayecto() {
		return new ModelAndView("agregarTrayecto.html");
	}
}
