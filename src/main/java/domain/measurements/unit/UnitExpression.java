package domain.measurements.unit;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class UnitExpression {

    private Set<SimpleUnit> proportionalUnits;
    private Set<SimpleUnit> invProportionalUnits;

    // At the time, this method only allows simple unit convertions (same base units)
    public Boolean isConvertibleTo(UnitExpression someUnitExpression) {
        return getBaseUnits(this.proportionalUnits) == getBaseUnits(someUnitExpression.getProportionalUnits())
            && getBaseUnits(this.invProportionalUnits) == getBaseUnits(someUnitExpression.getInvProportionalUnits());
    }

    public Integer getExponentForConvertionTo(UnitExpression someUnitExpression) {
        return getExponentSum(this.proportionalUnits)
            -  getExponentSum(someUnitExpression.getProportionalUnits())
            -  getExponentSum(this.invProportionalUnits)
            +  getExponentSum(someUnitExpression.getInvProportionalUnits());
    }

    public static Set<BaseUnit> getBaseUnits(Set<SimpleUnit> someSimpleUnits) {
        return someSimpleUnits.stream().map(SimpleUnit::getBaseUnit).collect(Collectors.toSet());
    }

    public static Integer getExponentSum(Set<SimpleUnit> someSimpleUnits) {
        return someSimpleUnits.stream().mapToInt(SimpleUnit::getExponent).sum();
    }
}
