package domain.measurements.unit;

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.twilio.twiml.voice.Sim;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UnitExpression {

  private Set<SimpleUnit> simpleUnits;

  public Boolean isConvertibleTo(UnitExpression someUE) {
    return haveSameBaseUnits(this.getProportionalUnits(), someUE.getProportionalUnits())
        && haveSameBaseUnits(this.getInvProportionalUnits(), someUE.getInvProportionalUnits());
  }

  public Set<SimpleUnit> getFilteredUnitSet(Predicate<SimpleUnit> someCondition) {
    return this.simpleUnits
        .stream()
        .filter(someCondition)
        .collect(Collectors.toSet());
  }

  public Set<SimpleUnit> getProportionalUnits() {
    return this.getFilteredUnitSet(SimpleUnit::isDirectlyProportional);
  }

  public Set<SimpleUnit> getInvProportionalUnits() {
    return this.getFilteredUnitSet(simpleUnit -> !simpleUnit.isDirectlyProportional());
  }

  public static Set<BaseUnit> getBaseUnits(Set<SimpleUnit> someSimpleUnits) {
    return someSimpleUnits.stream().map(SimpleUnit::getBaseUnit).collect(Collectors.toSet());
  }

  public static Boolean haveSameBaseUnits(Set<SimpleUnit> someUnits, Set<SimpleUnit> otherUnits) {
    return getBaseUnits(someUnits).equals(getBaseUnits(otherUnits));
  }

  public Integer getExponentSum() {
    return this.simpleUnits.stream().mapToInt(SimpleUnit::getSignedExponent).sum();
  }

  public Integer getExpForConvertionTo(UnitExpression someUnitExpression) {
    return this.getExponentSum() - someUnitExpression.getExponentSum();
  }

}
