package ddsutn.dtos.user;

import ddsutn.domain.organization.DocType;
import ddsutn.domain.organization.Member;
import ddsutn.security.user.StandardUser;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Setter
@Getter
public class StandardUserForView {
  public String username;
  public String password;
  public String name;
  public String surname;
  public String docType;
  public String docNumber;

  public StandardUser getUser() throws IOException {
    return new StandardUser(username, password, this.getMember());
  }

  public Member getMember() {
    return new Member(name, surname, DocType.valueOf(docType), docNumber);
  }
}