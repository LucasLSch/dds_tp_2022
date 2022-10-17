package domainTests;

import ddsutn.domain.journey.transport.Line;
import ddsutn.domain.journey.transport.PublicTransportType;
import ddsutn.domain.journey.transport.Stop;
import ddsutn.domain.location.Distance;
import ddsutn.domain.location.Location;
import ddsutn.domain.measurements.ConsumptionType;
import ddsutn.domain.measurements.EmissionFactor;
import ddsutn.domain.measurements.unit.BaseUnit;
import ddsutn.domain.measurements.unit.Proportionality;
import ddsutn.domain.measurements.unit.Unit;
import ddsutn.domain.measurements.unit.UnitExpression;
import ddsutn.domain.organization.DocType;
import org.hibernate.criterion.Restrictions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ddsutn.repositories.ConsumptionTypeRepo;
import ddsutn.repositories.LineRepo;
import ddsutn.repositories.UserRepo;
import ddsutn.security.user.Administrator;
import ddsutn.security.user.Registration;
import ddsutn.security.user.StandardUser;
import ddsutn.security.user.User;

import java.io.IOException;
import java.util.*;

public class TestDataFill {

  public TestDataFill() {
    this.fillRepos();
  }

  public void fillRepos() {
    this.createConsumptionType();
    this.createPublicTransport();
    this.createTransport();
    this.createLeg();
    this.createJourney();
    this.createMember();
    this.createSector();
    this.createActivityData();
    this.createOrganization();
    this.createTerritorialSector();
    this.createTerritorialSectorAgent();
    this.createUser();
  }

  private void createConsumptionType() {

    Set<Unit> unitsForNaturalGas = new HashSet<>(Arrays.asList(
            kilogram(Proportionality.DIRECT),
            cubicMeter(Proportionality.INVERSE)
    ));

    Set<Unit> unitsForOilGas = new HashSet<>(Arrays.asList(
            kilogram(Proportionality.DIRECT),
            liter(Proportionality.INVERSE)
    ));

    Set<Unit> unitsForCarbon = new HashSet<>(Arrays.asList(
            kilogram(Proportionality.DIRECT),
            kilogram(Proportionality.INVERSE)
    ));

    Set<Unit> unitsForElectricity = new HashSet<>(Arrays.asList(
            kilogram(Proportionality.DIRECT),
            second(Proportionality.DIRECT),
            kilowatt(Proportionality.INVERSE)
    ));

    Set<Unit> unitsForTransport = new HashSet<>(Arrays.asList(
            kilogram(Proportionality.DIRECT),
            kilometer(Proportionality.INVERSE)
    ));

    EmissionFactor[] efs = {
            new EmissionFactor(1d, unitsForNaturalGas),
            new EmissionFactor(2d, unitsForOilGas),
            new EmissionFactor(3d, unitsForOilGas),
            new EmissionFactor(4d, unitsForCarbon),
            new EmissionFactor(5d, unitsForOilGas),
            new EmissionFactor(6d, unitsForOilGas),
            new EmissionFactor(7d, unitsForElectricity),
            new EmissionFactor(8d, unitsForTransport)
    };

    ConsumptionType[] cts = {
            new ConsumptionType("Gas Natural",
                    new HashSet<>(Collections.singletonList(cubicMeter(Proportionality.DIRECT))),
                    "Combustion fija",
                    "Emisiones directas",
                    efs[0]),

            new ConsumptionType("Diesel/Gasoil",
                    new HashSet<>(Collections.singletonList(liter(Proportionality.DIRECT))),
                    "Combustion fija",
                    "Emisiones directas",
                    efs[1]),

            new ConsumptionType("Nafta",
                    new HashSet<>(Collections.singletonList(liter(Proportionality.DIRECT))),
                    "Combustion fija",
                    "Emisiones directas",
                    efs[2]),

            new ConsumptionType("Carbon",
                    new HashSet<>(Collections.singletonList(kilogram(Proportionality.DIRECT))),
                    "Combustion fija",
                    "Emisiones directas",
                    efs[3]),

            new ConsumptionType("Combustible Consumido - Gasoil",
                    new HashSet<>(Collections.singletonList(liter(Proportionality.DIRECT))),
                    "Combustion movil",
                    "Emisiones directas",
                    efs[4]),

            new ConsumptionType("Combustilbe Consumido - Nafta",
                    new HashSet<>(Collections.singletonList(liter(Proportionality.DIRECT))),
                    "Combustion movil",
                    "Emisiones directas",
                    efs[5]),

            new ConsumptionType("Electricidad",
                    new HashSet<>(Arrays.asList(kilowatt(Proportionality.DIRECT),
                            second(Proportionality.INVERSE))),
                    "Electricidad adquirida y consumida",
                    "Emisiones indirectas asociadas a la electricidad",
                    efs[6]),

            new ConsumptionType("Medio de Transporte - Distancia Recorrida",
                    new HashSet<>(Collections.singletonList(kilometer(Proportionality.DIRECT))),
                    "Logistica de productos y residuos",
                    "Otras emisiones indirectas que ocurren en fuentes no controladas por la organizacion",
                    efs[7]),
    };

    ConsumptionTypeRepo.getInstance().saveAll(cts);
  }

  private void createPublicTransport() {

    Stop stop1 = new Stop(
      new Location(null, "Calle 1", "1"),
      new Distance(20, kilometer(Proportionality.DIRECT)));
    Stop stop2 = new Stop(
      new Location(null, "Calle 2", "2"),
      new Distance(21, kilometer(Proportionality.DIRECT)));
    Stop stop3 = new Stop(
        new Location(null, "Calle 3", "3"),
        new Distance(22, kilometer(Proportionality.DIRECT)));

    Stop stop4 = new Stop(
        new Location(null, "Street 1", "1"),
        new Distance(10, kilometer(Proportionality.DIRECT)));
    Stop stop5 = new Stop(
        new Location(null, "Street 2", "2"),
        new Distance(11, kilometer(Proportionality.DIRECT)));
    Stop stop6 = new Stop(
        new Location(null, "Street 3", "3"),
        new Distance(12, kilometer(Proportionality.DIRECT)));

    Stop stop7 = new Stop(
        new Location(null, "Rue 1", "1"),
        new Distance(1, kilometer(Proportionality.DIRECT)));
    Stop stop8 = new Stop(
        new Location(null, "Rue 2", "2"),
        new Distance(2, kilometer(Proportionality.DIRECT)));
    Stop stop9 = new Stop(
        new Location(null, "Rue 3", "3"),
        new Distance(3, kilometer(Proportionality.DIRECT)));

    Line[] lines = {
        new Line(Arrays.asList(stop1, stop2, stop3), "SpanishLine", PublicTransportType.BUS),
        new Line(Arrays.asList(stop4, stop5, stop6), "EnglishLine", PublicTransportType.SUBWAY),
        new Line(Arrays.asList(stop7, stop8, stop9), "FrenchLine", PublicTransportType.TRAIN)
    };

    LineRepo.getInstance().saveAll(lines);
  }

  private void createUser() {
    try {
      User[] users = {
          new Registration()
              .setMember("Gonzalo", "Rodriguez Pardo", DocType.ID, "42.877.601")
              .registerStandardUser("Pardios", "1Contra$enia"),
          new Registration()
              .setMember("Lucas", "Schneider", DocType.ID, "42.396.327")
              .registerStandardUser("Pastita", "1Contra$enia"),
          new Registration()
              .setSectorAgent()
              .registerAgentUser("Agent_Smith", "1Contra$enia"),
          new Registration()
              .registerAdminUser("UltrAdmin", "1Contra$enia")
      };
      UserRepo.getInstance().saveAll(users);

    } catch (IOException e) {
      return;
    }}

  private void createTransport() {
  }

  private void createTerritorialSector() {
  }

  private void createTerritorialSectorAgent() {
  }

  private void createSector() {
  }

  private void createOrganization() {
  }

  private void createMember() {
  }

  private void createLeg() {
  }

  private void createJourney() {
  }

  private void createActivityData() {
  }

  // Utils

  private static Unit kilogram(Proportionality prop) {
    return new Unit(BaseUnit.GRAM, 3, prop);
  }

  private static Unit cubicMeter(Proportionality prop) {
    return new Unit(BaseUnit.LITER, 3, prop);
  }

  private static Unit liter(Proportionality prop) {
    return new Unit(BaseUnit.LITER, 0, prop);
  }

  private static Unit kilometer(Proportionality prop) {
    return new Unit(BaseUnit.METER, 3, prop);
  }

  private static Unit second(Proportionality prop) {
    return new Unit(BaseUnit.SECOND, 0, prop);
  }

  private static Unit kilowatt(Proportionality prop) {
    return new Unit(BaseUnit.WATT, 3, prop);
  }


  // Test random - dsps se borran o pasan a otro lugar

  @Test
  public void testDePruebaQueNOHaceNada() {
    ConsumptionTypeRepo.getInstance().getAll().forEach(ct -> {
      System.out.format("Id: %d\nNombre: %s\nUnidades: %s\nFE: %f\n\n",
          ct.getId(),
          ct.getName(),
          UnitExpression.printUnits(ct.getUnits()),
          ct.getEmissionFactor().getValue());
    });

    Assertions.assertTrue(true);
  }

  @Test
  public void testGetAllUsers() {
    List<User> users = UserRepo.getInstance().getAll();
    Assertions.assertEquals(4, users.size());
  }

  @Test
  public void testGetStandardUserByUsername() {
    StandardUser lucas = (StandardUser) UserRepo.getInstance()
        .getByCondition(Restrictions.eq("username", "Pastita"))
        .get(0);
    Assertions.assertEquals("Lucas", lucas.getMember().getName());
  }

  @Test
  public void testGetAdministratorByUsername() {
    Administrator admin = (Administrator) UserRepo.getInstance()
        .getByCondition(Restrictions.eq("username", "UltrAdmin"))
        .get(0);
    Assertions.assertTrue(Arrays.stream(new int[]{1, 2, 3, 4}).anyMatch(id -> id == admin.getId()));
  }

}
