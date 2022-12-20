package domainTests;

import ddsutn.domain.location.District;
import ddsutn.domain.location.Location;
import ddsutn.domain.measurements.*;
import ddsutn.domain.measurements.unit.BaseUnit;
import ddsutn.domain.measurements.unit.Proportionality;
import ddsutn.domain.measurements.unit.Unit;
import ddsutn.domain.organization.OrgType;
import ddsutn.domain.organization.Organization;
import ddsutn.domain.territories.TerritorialSector;
import ddsutn.domain.territories.TerritorialSectorAgent;
import ddsutn.domain.territories.TerritorialSectorType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class TerritorialSectorTest {
  private Organization dummyOrg;
  private Organization anotherDummyOrg;
  private TerritorialSector dummyTerrSector;
  private TerritorialSectorAgent dummyAgent;

  @BeforeEach
  public void beforeTest() {
    this.dummyOrg = new Organization(
            "marolio",
            new Location(new District(1), "calle", "123"),
            "le da sabor a tu vida",
            OrgType.COMPANY
    );

    this.anotherDummyOrg = new Organization(
            "ferrati",
            new Location(new District(2), "roma", "1233"),
            "falso",
            OrgType.GOVERNMENTAL
    );

    Set<Organization> orgs = new HashSet<>();
    orgs.add(dummyOrg);
    orgs.add(anotherDummyOrg);

    this.dummyTerrSector = new TerritorialSector(
            "El Gran Imperio Romano",
            TerritorialSectorType.STATE,
            orgs
    );

    this.dummyAgent = new TerritorialSectorAgent(dummyTerrSector);
  }

  private static Unit liter(Proportionality prop) {
    return new Unit(BaseUnit.LITER, 0, prop);
  }
  private static Unit kilogram(Proportionality prop) {
    return new Unit(BaseUnit.GRAM, 3, prop);
  }

  @Test
  public void aTerritorialSectorCanGetItsCF() {
    Set<Unit> ctunits = new HashSet<>();
    ctunits.add(kilogram(Proportionality.DIRECT));
    ctunits.add(liter(Proportionality.INVERSE));

    Set<Unit> efunits = new HashSet<>();
    efunits.add(liter(Proportionality.DIRECT));

    dummyOrg.addActivityData(new ActivityData(
            new ConsumptionType(
                    "Democracia",
                    ctunits,
                    "Apuñalar a Julio Cesar",
                    "Politica",
                    new EmissionFactor(0.1D, efunits)
            ),
            23D,
            PeriodicityFormat.MMAAAA,
            "030044"
    ));

    anotherDummyOrg.addActivityData(new ActivityData(
            new ConsumptionType(
                    "Canales",
                    ctunits,
                    "Construccion de sistema de drenaje",
                    "Arquitectura",
                    new EmissionFactor(1000D, efunits)
            ),
            800D,
            PeriodicityFormat.AAAA,
            "0046"
    ));

    CarbonFootprint tscf = this.dummyTerrSector.getCarbonFootprint();

    Assertions.assertEquals(tscf.getValue(), 800002.3d);
  }
}
