package ddsutn.domain.contact;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "contact")
public class Contact {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "email")
  private String email;

  @Convert(converter = NotificationMethodConverter.class)
  private NotificationMethod method;

  public Contact(String phoneNumber, String email, NotificationMethod method) {
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.method = method;
  }

  public void notify(String someMessage) {
    this.method.notify(this, someMessage);
  }

}
