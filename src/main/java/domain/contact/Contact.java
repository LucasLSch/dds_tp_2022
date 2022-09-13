package domain.contact;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Contact {

  private String phoneNumber;
  private String email;
  private NotificationMethod method;

  public void notify(String someMessage) {
    this.method.notify(this, someMessage);
  }

}
