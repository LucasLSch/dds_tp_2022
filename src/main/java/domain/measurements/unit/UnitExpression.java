package domain.measurements.unit;

import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UnitExpression {

  private Set<SimpleUnit> proportionalUnits;
  private Set<SimpleUnit> invProportionalUnits;

  // At the time, this method only allows simple unit convertions (same base units)
  public Boolean isConvertibleTo(UnitExpression someUE) {
    return getBaseUnits(proportionalUnits) == getBaseUnits(someUE.getProportionalUnits())
        && getBaseUnits(invProportionalUnits) == getBaseUnits(someUE.getInvProportionalUnits());
  }

  public Integer getExpForConvertionTo(UnitExpression someUnitExpression) {
    return getExponentSum(proportionalUnits)
        -  getExponentSum(someUnitExpression.getProportionalUnits())
        -  getExponentSum(invProportionalUnits)
        +  getExponentSum(someUnitExpression.getInvProportionalUnits());
  }

  public static Set<BaseUnit> getBaseUnits(Set<SimpleUnit> someSimpleUnits) {
    return someSimpleUnits.stream().map(SimpleUnit::getBaseUnit).collect(Collectors.toSet());
  }

  public static Integer getExponentSum(Set<SimpleUnit> someSimpleUnits) {
    return someSimpleUnits.stream().mapToInt(SimpleUnit::getExponent).sum();
  }
}
