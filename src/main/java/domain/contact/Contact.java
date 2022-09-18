package domain.contact;

import domain.organization.Member;
import domain.organization.Organization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
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

  @Enumerated(value = EnumType.STRING)
  private NotificationMethodType method;

  @ManyToOne(fetch = FetchType.LAZY)
  private Organization organization;

  @ManyToOne(fetch = FetchType.LAZY)
  private Member member;

  public void notify(String someMessage) {
    this.method.notify(this, someMessage);
  }

}
