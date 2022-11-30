package ddsutn.dtos.organization;

import ddsutn.domain.organization.Organization;
import ddsutn.domain.organization.Sector;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class OrganizationForView {
  public Long id;
  public String socialObj;
  public String location;
  public Integer membersAmount;
  public List<String> sectors;
  public String phoneNumber;
  public String email;

  public OrganizationForView(Organization someOrg) {
    this.setId(someOrg.getId());
    this.setSocialObj(someOrg.getSocialObjective());
    this.setLocation(someOrg.getLocation().print());
    this.setMembersAmount(someOrg.getMembers().size());
    this.setSectors(someOrg.getSectors().stream().map(Sector::getName).collect(Collectors.toList()));
//    ofv.setPhoneNumber(org.getContacts().get(0).getPhoneNumber());
//    ofv.setPhoneNumber(org.getContacts().get(0).getEmail());
  }

}
