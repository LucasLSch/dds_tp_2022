package ddsutn.dtos;

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
  public List<String> sectors;


  public MemberForView(Member someMember) {
    this.name = someMember.getName();
    this.surname = someMember.getSurname();
    //this.email = someMember.getContacts().stream().findFirst().get().getEmail();
    this.docType = someMember.getDocType().toString();
    this.documentNumber = someMember.getDocNumber();
    this.sectors = someMember.getSectors().stream().map(Objects::toString).collect(Collectors.toList());
  }

}
