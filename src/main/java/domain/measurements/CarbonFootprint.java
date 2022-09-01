package domain.measurements;

import domain.measurements.unit.Unit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
@Setter
public class CarbonFootprint {

  private Double value;
  private Unit unit;
  private LocalDate date;

  public CarbonFootprint getOn(Unit someUnit) {
    if(this.unit.isConvertibleTo(someUnit)) {
      this.value = this.value * Math.pow(10d, this.unit.getExponentForConvertionTo(someUnit));
      this.unit = someUnit;
    }
    return this;
  }

  public static CarbonFootprint sum(Unit someUnit, CarbonFootprint ... carbonFootprints) {
    Stream<CarbonFootprint> cfStream= Arrays.stream(carbonFootprints).sequential();
    cfStream = cfStream.map(cf -> cf.getOn(someUnit));
    Double totalValue = cfStream.mapToDouble(CarbonFootprint::getValue).sum();

    return new CarbonFootprint(totalValue, someUnit, LocalDate.now());
  }

}
