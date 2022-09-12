package domain.contact;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Contact {

  private String phoneNumber;
  private String email;
  private List<NotificationMethod> notificationMethodList;

  public void notify(String someMessage) {
    this.notificationMethodList.forEach(method -> method.notify(this, someMessage));
  }

}
