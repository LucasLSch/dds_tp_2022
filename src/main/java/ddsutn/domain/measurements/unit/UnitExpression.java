package ddsutn.domain.measurements.unit;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public abstract class UnitExpression {

  public static Boolean isConvertibleTo(Set<Unit> su1, Set<Unit> su2) {
    return haveSameBaseUnits(getProportionalUnits(su1), getProportionalUnits(su2))
        && haveSameBaseUnits(getInvProportionalUnits(su1), getInvProportionalUnits(su2));
  }

  public static Set<Unit> getFilteredUnitSet(Set<Unit> simpleUnits, Predicate<Unit> someCondition) {
    return simpleUnits
        .stream()
        .filter(someCondition)
        .collect(Collectors.toSet());
  }

  public static Set<Unit> getProportionalUnits(Set<Unit> simpleUnits) {
    return getFilteredUnitSet(simpleUnits, Unit::isDirectlyProportional);
  }

  public static Set<Unit> getInvProportionalUnits(Set<Unit> simpleUnits) {
    return getFilteredUnitSet(simpleUnits, simpleUnit -> !simpleUnit.isDirectlyProportional());
  }

  public static Set<BaseUnit> getBaseUnits(Set<Unit> someSimpleUnits) {
    return someSimpleUnits.stream().map(Unit::getBaseUnit).collect(Collectors.toSet());
  }

  public static Boolean haveSameBaseUnits(Set<Unit> someUnits, Set<Unit> otherUnits) {
    return getBaseUnits(someUnits).equals(getBaseUnits(otherUnits));
  }

  public static Integer getExponentSum(Set<Unit> simpleUnits) {
    return simpleUnits.stream().mapToInt(Unit::getSignedExponent).sum();
  }

  public static Integer getExpForConvertionTo(Set<Unit> su1, Set<Unit> su2) {
    return getExponentSum(su1) - getExponentSum(su2);
  }

  public static String printUnits(Set<Unit> someUnits) {
    Set<Unit> directUnits = someUnits
        .stream()
        .filter(Unit::isDirectlyProportional)
        .collect(Collectors.toSet());

    Set<Unit> inverseUnits = someUnits
        .stream()
        .filter(unit -> !unit.isDirectlyProportional())
        .collect(Collectors.toSet());

    if (inverseUnits.isEmpty()) {
      return printSamePropUnits(directUnits);
    }

    return printSamePropUnits(directUnits) + " / " + printSamePropUnits(inverseUnits);
  }

  private static String printSamePropUnits(Set<Unit> someUnits) {
    String finalString = "(1)";

    if (!someUnits.isEmpty()) {
      finalString = someUnits
          .stream()
          .map(unit -> " * " + unit.print())
          .reduce((s1, s2) -> s1 + s2)
          .orElse("")
          .substring(3);
    }

    return finalString;
  }

}
