package domain.measurements;

import domain.measurements.unit.UnitExpression;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CarbonFootprint {

  private Double value;
  private UnitExpression unitExpression;
  private LocalDate date;

  public CarbonFootprint getOn(UnitExpression someUE) {
    if (this.unitExpression.isConvertibleTo(someUE)) {
      this.value = value * Math.pow(10d, this.unitExpression.getExpForConvertionTo(someUE));
      this.unitExpression = someUE;
    }
    return this;
  }

  public static CarbonFootprint sum(UnitExpression someUE, CarbonFootprint... carbonFootprints) {
    Stream<CarbonFootprint> cfStream = Arrays.stream(carbonFootprints).sequential();
    cfStream = cfStream.map(cf -> cf.getOn(someUE));
    Double totalValue = cfStream.mapToDouble(CarbonFootprint::getValue).sum();

    return new CarbonFootprint(totalValue, someUE, LocalDate.now());
  }

}
