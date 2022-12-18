package ddsutn.dtos.organization;

import ddsutn.domain.location.Location;
import ddsutn.domain.organization.OrgType;
import ddsutn.domain.organization.Organization;
import ddsutn.domain.organization.Sector;
import ddsutn.dtos.member.LocationForView;
import ddsutn.services.DistrictSvc;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
public class OrganizationForView {
  public Long id;
  public String socialObj;
  public String clasification;
  public String orgType;
  public Long territorialSectorId;
  public LocationForView location;
  public Integer membersAmount;
  public List<String> sectors;
  public String phoneNumber;
  public String email;

  public OrganizationForView(Organization someOrg) {
    this.setId(someOrg.getId());
    this.setSocialObj(someOrg.getSocialObjective());
    this.location = new LocationForView(someOrg.getLocation());
    this.setMembersAmount(someOrg.getMembers().size());
    this.setSectors(someOrg.getSectors().stream().map(Sector::getName).collect(Collectors.toList()));
    this.setClasification(someOrg.getClasification());
    this.setOrgType(someOrg.getType().name());
//    ofv.setPhoneNumber(org.getContacts().get(0).getPhoneNumber());
//    ofv.setPhoneNumber(org.getContacts().get(0).getEmail());
  }

  public Organization toOrganization(DistrictSvc districtSvc) {
      return new Organization(this.socialObj, this.location.toLocation(districtSvc), this.clasification, OrgType.valueOf(orgType));
  }

}
