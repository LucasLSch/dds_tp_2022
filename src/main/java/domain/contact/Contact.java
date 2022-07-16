package domain.contact;

import lombok.AllArgsConstructor;
import java.util.List;

@AllArgsConstructor
public class Contact {

  private String phone;
  private String email;
  private List<NotificationMethod> notificationMethodList;

  public void notify(String someMessage) {
    this.notificationMethodList.forEach(method -> method.notify(this, someMessage));
  }

}
