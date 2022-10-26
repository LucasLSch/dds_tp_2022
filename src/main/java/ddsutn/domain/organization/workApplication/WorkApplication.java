package ddsutn.domain.organization.workApplication;

import ddsutn.domain.organization.Member;
import ddsutn.domain.organization.Sector;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "work_application")
@IdClass(WorkApplicationId.class)
public class WorkApplication {

  @Id
  @ManyToOne
  @JoinColumn(name = "sector_id", referencedColumnName = "id")
  private Sector sector;

  @Id
  @ManyToOne
  @JoinColumn(name = "member_id", referencedColumnName = "id")
  private Member member;

  @Column(name = "state")
  @Enumerated(value = EnumType.STRING)
  private WorkApplicationState state;

  public Boolean stateIs(WorkApplicationState someState) {
    return this.state.equals(someState);
  }

  public void accept() {
    this.sector.addMember(this.member);
    this.member.addSector(this.sector);
    this.state = WorkApplicationState.ACCEPTED;
  }

  public void reject() {
    this.state = WorkApplicationState.REJECTED;
  }

}
