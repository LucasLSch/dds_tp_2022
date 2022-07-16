package domain.measurements;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.StandardException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
@Setter
public class CarbonFootprint {

  private Double value;
  private String unit;
  private LocalDate date;

  public CarbonFootprint getOn(String someUnit) {
    //TODO
    return this;
  }

  public static CarbonFootprint sum(String someUnit, CarbonFootprint ... carbonFootprints) {
    Stream<CarbonFootprint> cfStream= Arrays.stream(carbonFootprints).sequential();
    cfStream = cfStream.map(cf -> cf.getOn(someUnit));
    Double totalValue = cfStream.mapToDouble(CarbonFootprint::getValue).sum();

    return new CarbonFootprint(totalValue, someUnit, LocalDate.now());
  }

}
