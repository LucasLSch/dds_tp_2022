package ddsutn.domain.location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Province {

  @Id
  private Integer id;
  private String name;

  @ManyToOne
  @JoinColumn(name = "country_id")
  private Country country;

}
