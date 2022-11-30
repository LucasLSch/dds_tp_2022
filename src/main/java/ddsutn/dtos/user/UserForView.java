package ddsutn.dtos.user;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserForView {
  public String username;
  public String password;
  public String userType;
}