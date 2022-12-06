package ddsutn.controllers;

import ddsutn.domain.journey.Journey;
import ddsutn.domain.journey.Leg;
import ddsutn.domain.journey.transport.*;
import ddsutn.domain.location.District;
import ddsutn.domain.location.Location;
import ddsutn.domain.organization.Member;
import ddsutn.dtos.member.JourneyForView;
import ddsutn.dtos.member.MemberForView;
import ddsutn.dtos.user.UserForView;
import ddsutn.security.user.StandardUser;
import ddsutn.security.user.User;
import ddsutn.services.JourneySvc;
import ddsutn.services.MemberSvc;
import ddsutn.services.UserSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/miembros")
public class MemberController {

  private final String memberDetailHtml = "/members/memberDetails";
  private final String memberJourneysHtml = "/members/journeys";

  @Autowired
  private MemberSvc memberSvc;

  @Autowired
  private UserSvc userSvc;

  @Autowired
  private JourneySvc journeySvc;

  @GetMapping("/{id}")
  public ModelAndView showMember(@PathVariable Long id) {
    ModelAndView mav = new ModelAndView();
    Member read = memberSvc.findById(id);

    try {
      validateUser(id, "redirect:/miembros/{id}");
      validateMember(read);
    } catch (IncorrectUserException iue) {
      mav.setViewName(iue.getRedirect());
      return mav;
    } catch (UserNotFoundException unfe) {
      mav.setStatus(unfe.getStatus());
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

    //dsadsa
    District dt = new District(1);

    Leg[] legs = {
        new Leg(new Location(dt, "Rue1", "1"),
            new Location(dt, "Rue1", "2"),
            new EcoFriendly(EcoFriendlyType.BICYCLE)),

        new Leg(new Location(dt, "Rue1", "2"),
            new Location(dt, "Rue1", "3"),
            new ParticularVehicle(0.6, ParticularVehicleType.CAR, Fuel.OIL)),

        new Leg(new Location(dt, "Rue1", "3"),
            new Location(dt, "Rue1", "4"),
            new HiredService(0.3, HiredServiceType.APPLICATION, "SUBER"))
    };

    JourneyForView journey = new JourneyForView(new Journey(Arrays.asList(legs)));
    List<JourneyForView> journeyss = Arrays.asList(journey);

    /// dsadsa

    try {
      validateUser(id, "redirect:/miembros/{id}/trayectos");
      validateMember(read);
    } catch (IncorrectUserException iue) {
      mav.setViewName(iue.getRedirect());
      return mav;
    } catch (UserNotFoundException unfe) {
      mav.setStatus(unfe.getStatus());
      return mav;
    }

    List<JourneyForView> journeys = read.getJourneys().stream().map(JourneyForView::new).collect(Collectors.toList());
    mav.addObject("allJourneys", journeyss);
    mav.addObject("member", new MemberForView(read));
    mav.addObject("newJourney", new JourneyForView());
    mav.addObject("allTransportTypes", this.transportTypes());
    mav.setViewName(memberJourneysHtml);
    return mav;
  }

  private List<String> transportTypes() {
    return Arrays.asList(
        "-- Elija un tipo de Transporte --",
        "EcoFriendly",
        "Vehículo Particular",
        "Servicio Contratado",
        "Transporte Público");
  }

  @PostMapping("/{m_id}/trayectos/{t_id}")
  public ModelAndView deleteJourney(@PathVariable Long m_id, @PathVariable Long t_id) {
    ModelAndView mav = new ModelAndView();

    Member m_read = memberSvc.findById(m_id);

    try {
      validateUser(m_id, "");
      validateMember(m_read);
    } catch (IncorrectUserException iue) {
      mav.setStatus(HttpStatus.FORBIDDEN);
      return mav;
    } catch (UserNotFoundException unfe) {
      mav.setStatus(unfe.getStatus());
      return mav;
    }

    m_read.getJourneys().removeIf(journey -> journey.getId().equals(t_id));
    journeySvc.deleteById(t_id);

    mav.setViewName("redirect:/miembros/" + m_id.toString() + "/trayectos");
    return mav;
  }

//  @PostMapping("/{m_id}/trayectos")
//  public ModelAndView newJourney(@PathVariable Long m_id, @ModelAttribute("newJourney") JourneyForView journey) {
//    ModelAndView mav = new ModelAndView();
//
//    Member m_read = memberSvc.findById(m_id);
//
//    try {
//      validateUser(m_id, "");
//      validateMember(m_read);
//    } catch (IncorrectUserException iue) {
//      mav.setStatus(HttpStatus.FORBIDDEN);
//      return mav;
//    } catch (UserNotFoundException unfe) {
//      mav.setStatus(unfe.getStatus());
//      return mav;
//    }
//
//    mav.setViewName("redirect:/miembros/" + m_id.toString() + "/trayectos");
//    return mav;
//  }

  private void validateUser(Long id, String redirectOption) {
    String name = SecurityContextHolder.getContext().getAuthentication().getName();
    Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    User myUser = userSvc.loadUserByUsername(name);

    if (authorities.stream().noneMatch(ga -> ga.getAuthority().equals("ADMINISTRATOR_USER")) &&
            !((StandardUser) myUser).getMember().getId().equals(id)) {
      throw new IncorrectUserException(((StandardUser) myUser).getMember().getId(), redirectOption);
    }
  }

  private void validateMember(Member member) {
    if (member == null) {
      throw new UserNotFoundException();
    }
  }

  private static class IncorrectUserException extends RuntimeException {
    private static final String msg = "Hey! No podes acceder a algo que no es tuyo!!";
    private final Long correctId;
    private final String redirect;

    public IncorrectUserException(Long correctId, String redirect) {
      super(msg);
      this.correctId = correctId;
      this.redirect = redirect;
    }

    private Long getCorrectId() {
      return this.correctId;
    }

    public String getRedirect() {
      return this.redirect.replace("{id}", this.getCorrectId().toString());
    }
  }

  private static class UserNotFoundException extends RuntimeException {
    private static final String msg = "El miembro no existe!!";
    private static final HttpStatus status = HttpStatus.NOT_FOUND;

    public UserNotFoundException() {
      super(msg);
    }

    public HttpStatus getStatus() {
      return status;
    }
  }

}
