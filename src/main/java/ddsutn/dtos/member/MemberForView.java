package ddsutn.dtos.member;

import ddsutn.domain.contact.Contact;
import ddsutn.domain.organization.Member;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
public class MemberForView {
  public String name;
  public String surname;
  public String email;
  public String docType;
  public String documentNumber;
  public String phoneNumber;
  public List<String> sectors;


  public MemberForView(Member someMember) {
    this.name = someMember.getName();
    this.surname = someMember.getSurname();
    this.email = someMember.getContacts().stream().map(Contact::getEmail).findFirst().orElse("");
    this.phoneNumber = someMember.getContacts().stream().map(Contact::getPhoneNumber).findFirst().orElse("");
    this.docType = someMember.getDocType().toString();
    this.documentNumber = someMember.getDocNumber();
    this.sectors = someMember.getSectors().stream().map(Objects::toString).collect(Collectors.toList());
  }

}


