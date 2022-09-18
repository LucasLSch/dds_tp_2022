package domain.location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Embeddable
public class District {

  @Column(name = "district_number")
  private Integer id;
  @Transient
  private String name;
  @Transient
  private Integer postalCode;
  @Transient
  private Municipality municipality;

  public District(Integer id) {
    this.id = id;
  }

}
