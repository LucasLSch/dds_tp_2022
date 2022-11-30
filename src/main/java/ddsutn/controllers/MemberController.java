package ddsutn.controllers;

import ddsutn.domain.organization.Member;
import ddsutn.dtos.member.JourneyForView;
import ddsutn.dtos.member.MemberForView;
import ddsutn.services.MemberSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/miembros")
public class MemberController {

  private final String memberDetailHtml = "/members/memberDetails";
  private final String memberJourneysHtml = "/members/journeys";

  @Autowired
  private MemberSvc memberSvc;

  @GetMapping("/{id}")
  public ModelAndView showMember(@PathVariable Long id) {
    ModelAndView mav = new ModelAndView();

    Member read = memberSvc.findById(id);

    if (read == null) {
      mav.setStatus(HttpStatus.NOT_FOUND);
      return mav;
    }

    MemberForView member = new MemberForView(read);
    mav.addObject("member", member);
    mav.setViewName(memberDetailHtml);
    return mav;
  }


  @GetMapping("/{id}/trayectos")
  public ModelAndView showJourneys(@PathVariable Long id) {
    ModelAndView mav = new ModelAndView();

    Member read = memberSvc.findById(id);

    if (read == null) {
      mav.setStatus(HttpStatus.NOT_FOUND);
      return mav;
    }

    List<JourneyForView> journeys = read.getJourneys().stream().map(JourneyForView::new).collect(Collectors.toList());
    mav.addObject("allJourneys", journeys);
    mav.setViewName(memberJourneysHtml);
    return mav;
  }

//  @GetMapping("/registrarTrayecto")
//  public String registerJourney(Model model) {
//    return "Estas por registrar unTrayecto";
//  }
//
//  @PostMapping("/registrarTrayecto")
//  public String registerJourney(@RequestBody ActivityData ad, Model model) {
//    return "Agregaste un trayecto :D";
//  }
//

}
