package ddsutn.security.user;

import ddsutn.domain.exceptions.NullMemberException;
import ddsutn.domain.organization.DocType;
import ddsutn.domain.organization.Member;
import ddsutn.domain.organization.Organization;
import ddsutn.domain.organization.Sector;
import ddsutn.domain.territories.TerritorialSectorAgent;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.io.IOException;

@Setter
@Getter
public class Registration {

  private Member member;

  private Organization organization;

  private TerritorialSectorAgent territorialSectorAgent;

  public User registerStandardUser(@NonNull String username,
                                   @NonNull String password) throws IOException {
    validateNullMember();
    return new StandardUser(username, password, getMember());
  }

  private void validateNullMember() {
    if (getMember() == null) {
      throw new NullMemberException("Member");
    }
  }

  public User registerAdminUser(@NonNull String username,
                                @NonNull String password) throws IOException {
    return new Administrator(username, password);
  }

  public User registerAgentUser(@NonNull String username,
                                @NonNull String password) throws IOException {
    validateNullSectorAgent();
    return new TerritorialAgentUser(username, password, getTerritorialSectorAgent());
  }

  private void validateNullSectorAgent() {
    if (getTerritorialSectorAgent() == null) {
      throw new NullMemberException("TerritorialSectorAgent");
    }
  }

  public Registration setMember(
          @NonNull String name,
          @NonNull String surname,
          @NonNull DocType docType,
          @NonNull String idNumber
  ) {
    setMember(new Member(name, surname, docType, idNumber));
    return this;
  }

  public Registration setSectorAgent() {
    setTerritorialSectorAgent(new TerritorialSectorAgent());
    return this;
  }

  public Registration worksForOrganization(Organization organization) {
    if (!(organization == null)) {
      setOrganization(organization);
    }
    return this;
  }

  public Registration worksInSector(Sector sector) {
    if (!(sector == null)) {
      getMember().applyToSector(sector);
    }
    return this;
  }
}
