package domain.measurements.unit;

import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UnitExpression {

  private Set<SimpleUnit> propUnits;
  private Set<SimpleUnit> invPropUnits;

  // At the time, this method only allows simple unit convertions (same base units)
  public Boolean isConvertibleTo(UnitExpression someUE) {
    return getBaseUnits(propUnits).equals(getBaseUnits(someUE.getPropUnits()))
            && getBaseUnits(invPropUnits).equals(getBaseUnits(someUE.getInvPropUnits()));
  }

  public Integer getExpForConvertionTo(UnitExpression someUnitExpression) {
    return getExponentSum(propUnits)
        -  getExponentSum(someUnitExpression.getPropUnits())
        -  getExponentSum(invPropUnits)
        +  getExponentSum(someUnitExpression.getInvPropUnits());
  }

  public static Set<BaseUnit> getBaseUnits(Set<SimpleUnit> someSimpleUnits) {
    return someSimpleUnits.stream().map(SimpleUnit::getBaseUnit).collect(Collectors.toSet());
  }

  public static Integer getExponentSum(Set<SimpleUnit> someSimpleUnits) {
    return someSimpleUnits.stream().mapToInt(SimpleUnit::getExponent).sum();
  }
}
