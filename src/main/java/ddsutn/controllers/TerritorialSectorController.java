package ddsutn.controllers;

import ddsutn.domain.territories.TerritorialSector;
import ddsutn.domain.territories.TerritorialSectorType;
import ddsutn.dtos.TerritorialSectorForView;
import ddsutn.services.TerritorialSectorSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/sectoresTerritoriales")
public class TerritorialSectorController {

  private final String newTerritorialSector = "/territorialSectors/newTerritorialSector";

  @Autowired
  private TerritorialSectorSvc territorialSectorSvc;

  @PreAuthorize("hasAuthority('ADMINISTRATOR_USER')")
  @GetMapping("/nuevoSectorTerritorial")
  public ModelAndView createNewTerritorialSector() {
    ModelAndView mav = new ModelAndView();

    mav.addObject("newTerritorialSector", new TerritorialSectorForView());
    mav.addObject("allTsTypes", Arrays.stream(TerritorialSectorType.values()).map(Enum::name).collect(Collectors.toList()));

    mav.setViewName(newTerritorialSector);

    return mav;
  }

  @PreAuthorize("hasAuthority('ADMINISTRATOR_USER')")
  @PostMapping("")
  public ModelAndView saveNewTerritorialSector(@ModelAttribute TerritorialSectorForView tsfv) {
    ModelAndView mav = new ModelAndView();
    TerritorialSector newTerritorialSector = tsfv.toTerritorialSector();
    this.territorialSectorSvc.save(newTerritorialSector);
    mav.setViewName("home/home");
    return mav;
  }

}
