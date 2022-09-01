package domain.measurements;

import domain.measurements.unit.UnitExpression;
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
  private UnitExpression unitExpression;
  private LocalDate date;

  public CarbonFootprint getOn(UnitExpression someUnitExpression) {
    if(this.unitExpression.isConvertibleTo(someUnitExpression)) {
      this.value = this.value * Math.pow(10d, this.unitExpression.getExponentForConvertionTo(someUnitExpression));
      this.unitExpression = someUnitExpression;
    }
    return this;
  }

  public static CarbonFootprint sum(UnitExpression someUnitExpression, CarbonFootprint ... carbonFootprints) {
    Stream<CarbonFootprint> cfStream= Arrays.stream(carbonFootprints).sequential();
    cfStream = cfStream.map(cf -> cf.getOn(someUnitExpression));
    Double totalValue = cfStream.mapToDouble(CarbonFootprint::getValue).sum();

    return new CarbonFootprint(totalValue, someUnitExpression, LocalDate.now());
  }

}
