package domain.contact;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Contact {

  private String phone;
  private String email;
  private List<NotificationMethod> notificationMethodList;

  public void notify(String someMessage) {
    this.notificationMethodList.forEach(method -> method.notify(this, someMessage));
  }

}
