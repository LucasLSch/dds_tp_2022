package domain.measurements.unit;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class Unit {

    private Set<SimpleUnit> proportionalUnits;
    private Set<SimpleUnit> invProportionalUnits;

    // At the time, this method only allows simple unit convertions (same base units)
    public Boolean isConvertibleTo(Unit someUnit) {
        return getBaseUnits(this.proportionalUnits) == getBaseUnits(someUnit.getProportionalUnits())
            && getBaseUnits(this.invProportionalUnits) == getBaseUnits(someUnit.getInvProportionalUnits());
    }

    public Integer getExponentForConvertionTo(Unit someUnit) {
        return getExponentSum(this.proportionalUnits)
            -  getExponentSum(someUnit.getProportionalUnits())
            -  getExponentSum(this.invProportionalUnits)
            +  getExponentSum(someUnit.getInvProportionalUnits());
    }

    public static Set<BaseUnit> getBaseUnits(Set<SimpleUnit> someSimpleUnits) {
        return someSimpleUnits.stream().map(SimpleUnit::getBaseUnit).collect(Collectors.toSet());
    }

    public static Integer getExponentSum(Set<SimpleUnit> someSimpleUnits) {
        return someSimpleUnits.stream().mapToInt(SimpleUnit::getExponent).sum();
    }
}
