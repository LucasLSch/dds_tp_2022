package ddsutn.domain.organization.workApplication;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class WorkApplicationId implements Serializable {

  private Long sector;
  private Long member;

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    WorkApplicationId that = (WorkApplicationId) obj;
    return Objects.equals(sector, that.sector) && Objects.equals(member, that.member);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sector, member);
  }

}
