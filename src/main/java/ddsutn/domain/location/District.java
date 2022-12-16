package ddsutn.domain.location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class District {

  @Id
  private Integer id;
  private String name;
  private Integer postalCode;

  @ManyToOne
  @JoinColumn(name = "municipality_id")
  private Municipality municipality;

  public District(Integer id) {
    this.id = id;
  }

}
