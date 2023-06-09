package domainTests;

import ddsutn.domain.journey.Journey;
import ddsutn.domain.journey.Leg;
import ddsutn.domain.journey.transport.*;
import ddsutn.domain.location.Distance;
import ddsutn.domain.location.Location;
import ddsutn.domain.measurements.ActivityData;
import ddsutn.domain.measurements.ConsumptionType;
import ddsutn.domain.measurements.EmissionFactor;
import ddsutn.domain.measurements.PeriodicityFormat;
import ddsutn.domain.measurements.unit.BaseUnit;
import ddsutn.domain.measurements.unit.Proportionality;
import ddsutn.domain.measurements.unit.Unit;
import ddsutn.domain.organization.DocType;
import ddsutn.domain.organization.Member;
import ddsutn.domain.organization.OrgType;
import ddsutn.domain.organization.Organization;
import ddsutn.domain.territories.TerritorialSector;
import ddsutn.domain.territories.TerritorialSectorAgent;
import ddsutn.domain.territories.TerritorialSectorType;
import ddsutn.security.user.Registration;
import ddsutn.security.user.User;
import ddsutn.services.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class TestDataFill {

  @Autowired
  protected UserSvc userSvc;

  @Autowired
  protected TransportSvc transportSvc;

  @Autowired
  protected ConsumptionTypeSvc consumptionTypeSvc;

  @Autowired
  protected TerritorialSectorSvc territorialSectorSvc;

  @Autowired
  protected TerrirotialSectorAgentSvc terrirotialSectorAgentSvc;

  @Autowired
  protected LocationSvc locationSvc;

  @Autowired
  protected JourneySvc journeySvc;

  @Autowired
  protected MemberSvc memberSvc;

  @Autowired
  protected OrganizationSvc organizationSvc;

  @Autowired
  protected ActivityDataSvc activityDataSvc;

  public TestDataFill() {
    //this.fillRepos();
  }

  public static Unit kilogram(Proportionality prop) {
    return new Unit(BaseUnit.GRAM, 3, prop);
  }

  public static Unit cubicMeter(Proportionality prop) {
    return new Unit(BaseUnit.LITER, 3, prop);
  }

  public static Unit liter(Proportionality prop) {
    return new Unit(BaseUnit.LITER, 0, prop);
  }

  public static Unit kilometer(Proportionality prop) {
    return new Unit(BaseUnit.METER, 3, prop);
  }

  public static Unit second(Proportionality prop) {
    return new Unit(BaseUnit.SECOND, 0, prop);
  }

  private static Unit hour(Proportionality prop) {
    return new Unit(BaseUnit.HOUR, 0, prop);
  }

  public static Unit kilowatt(Proportionality prop) {
    return new Unit(BaseUnit.WATT, 3, prop);
  }

  public void fillRepos() {
    this.createConsumptionType();
    this.createPublicTransport();
    this.createTransport();
    this.createJourney();
    this.createMember();
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

    this.consumptionTypeSvc.saveAll(Arrays.asList(cts));
  }

  private void createPublicTransport() {

    Stop stop1 = new Stop(
        new Location(null, "Calle 1", "1"),
        new Distance(20d, kilometer(Proportionality.DIRECT)));
    Stop stop2 = new Stop(
        new Location(null, "Calle 2", "2"),
        new Distance(21d, kilometer(Proportionality.DIRECT)));
    Stop stop3 = new Stop(
        new Location(null, "Calle 3", "3"),
        new Distance(22d, kilometer(Proportionality.DIRECT)));

    Stop stop4 = new Stop(
        new Location(null, "Street 1", "1"),
        new Distance(10d, kilometer(Proportionality.DIRECT)));
    Stop stop5 = new Stop(
        new Location(null, "Street 2", "2"),
        new Distance(11d, kilometer(Proportionality.DIRECT)));
    Stop stop6 = new Stop(
        new Location(null, "Street 3", "3"),
        new Distance(12d, kilometer(Proportionality.DIRECT)));

    Stop stop7 = new Stop(
        new Location(null, "Rue 1", "1"),
        new Distance(1d, kilometer(Proportionality.DIRECT)));
    Stop stop8 = new Stop(
        new Location(null, "Rue 2", "2"),
        new Distance(2d, kilometer(Proportionality.DIRECT)));
    Stop stop9 = new Stop(
        new Location(null, "Rue 3", "3"),
        new Distance(3d, kilometer(Proportionality.DIRECT)));

    Line line1 = new Line(Arrays.asList(stop1, stop2, stop3), "SpanishLine", PublicTransportType.BUS);
    Line line2 = new Line(Arrays.asList(stop4, stop5, stop6), "EnglishLine", PublicTransportType.SUBWAY);
    Line line3 = new Line(Arrays.asList(stop7, stop8, stop9), "FrenchLine", PublicTransportType.TRAIN);

    PublicTransport[] publicTransports = {
        new PublicTransport(line1, stop1, stop2),
        new PublicTransport(line1, stop2, stop3),
        new PublicTransport(line1, stop1, stop2),
        new PublicTransport(line2, stop4, stop6),
        new PublicTransport(line3, stop7, stop9)
    };

    transportSvc.saveAll(Arrays.asList(publicTransports));
  }

  private void createTransport() {
    Transport[] transports = {
        new EcoFriendly(EcoFriendlyType.BICYCLE),
        new EcoFriendly(EcoFriendlyType.SCOOTER),
        new EcoFriendly(EcoFriendlyType.WALKING),
        new ParticularVehicle(ParticularVehicleType.CAR, Fuel.OIL),
        new ParticularVehicle(ParticularVehicleType.VAN, Fuel.GASOIL),
        new ParticularVehicle(ParticularVehicleType.CAR, Fuel.GNC),
        new ParticularVehicle(ParticularVehicleType.CAR, Fuel.ELECTRIC),
        new ParticularVehicle(ParticularVehicleType.MOTORBIKE, Fuel.OIL),
        new HiredService(HiredServiceType.TAXI, "Taxi-Driver"),
        new HiredService(HiredServiceType.APPLICATION, "SUBER"),
        new HiredService(HiredServiceType.APPLICATION, "ECOFY"),
        new HiredService(HiredServiceType.CAB, "Taxin't")
    };

    transportSvc.saveAll(Arrays.asList(transports));
  }

  // Utils

  private void createJourney() {

    // Orgs en locations 4L, 6L

    Leg[] legs = {
        new Leg(this.locationSvc.findById(1L), this.locationSvc.findById(2L), this.transportSvc.findById(1L)),
        new Leg(this.locationSvc.findById(2L), this.locationSvc.findById(3L), this.transportSvc.findById(2L)),
        new Leg(this.locationSvc.findById(3L), this.locationSvc.findById(4L), this.transportSvc.findById(6L)),

        new Leg(this.locationSvc.findById(4L), this.locationSvc.findById(3L), this.transportSvc.findById(6L)),
        new Leg(this.locationSvc.findById(3L), this.locationSvc.findById(2L), this.transportSvc.findById(2L)),
        new Leg(this.locationSvc.findById(2L), this.locationSvc.findById(1L), this.transportSvc.findById(1L)),


        new Leg(this.locationSvc.findById(4L), this.locationSvc.findById(5L), this.transportSvc.findById(7L)),
        new Leg(this.locationSvc.findById(5L), this.locationSvc.findById(6L), this.transportSvc.findById(8L)),

        new Leg(this.locationSvc.findById(6L), this.locationSvc.findById(5L), this.transportSvc.findById(8L)),
        new Leg(this.locationSvc.findById(5L), this.locationSvc.findById(4L), this.transportSvc.findById(7L)),


        new Leg(this.locationSvc.findById(7L), this.locationSvc.findById(8L), this.transportSvc.findById(9L)),
        new Leg(this.locationSvc.findById(8L), this.locationSvc.findById(9L), this.transportSvc.findById(10L)),

        new Leg(this.locationSvc.findById(9L), this.locationSvc.findById(8L), this.transportSvc.findById(10L)),
        new Leg(this.locationSvc.findById(8L), this.locationSvc.findById(7L), this.transportSvc.findById(9L)),


        new Leg(this.locationSvc.findById(5L), this.locationSvc.findById(4L), this.transportSvc.findById(11L)),

        new Leg(this.locationSvc.findById(4L), this.locationSvc.findById(5L), this.transportSvc.findById(11L)),


        new Leg(this.locationSvc.findById(3L), this.locationSvc.findById(7L), this.transportSvc.findById(12L)),
        new Leg(this.locationSvc.findById(7L), this.locationSvc.findById(6L), this.transportSvc.findById(13L)),

        new Leg(this.locationSvc.findById(6L), this.locationSvc.findById(7L), this.transportSvc.findById(13L)),
        new Leg(this.locationSvc.findById(7L), this.locationSvc.findById(3L), this.transportSvc.findById(12L))
    };

    Journey[] journeys = {
        new Journey(Arrays.asList(legs[0], legs[1], legs[2])),
        new Journey(Arrays.asList(legs[3], legs[4], legs[5])),

        new Journey(Arrays.asList(legs[6], legs[7])),
        new Journey(Arrays.asList(legs[8], legs[9])),

        new Journey(Arrays.asList(legs[10], legs[11])),
        new Journey(Arrays.asList(legs[12], legs[13])),

        new Journey(Collections.singletonList(legs[14])),
        new Journey(Collections.singletonList(legs[15])),

        new Journey(Arrays.asList(legs[16], legs[17])),
        new Journey(Arrays.asList(legs[18], legs[19]))
    };

    this.journeySvc.saveAll(Arrays.asList(journeys));
  }

  private void createTerritorialSector() {
    TerritorialSector[] territorialSectors = {
        new TerritorialSector("Pepe1", TerritorialSectorType.CITY, new HashSet<>()),
        new TerritorialSector("Pepe2", TerritorialSectorType.STATE, new HashSet<>()),
        new TerritorialSector("Pepe3", TerritorialSectorType.CITY, new HashSet<>()),
        new TerritorialSector("Pepe4", TerritorialSectorType.STATE, new HashSet<>())
    };

    territorialSectorSvc.saveAll(Arrays.asList(territorialSectors));
  }

  private void createTerritorialSectorAgent() {
    TerritorialSectorAgent[] territorialSectorAgents = {
        new TerritorialSectorAgent(territorialSectorSvc.findById(1L)),
        new TerritorialSectorAgent(territorialSectorSvc.findById(2L)),
        new TerritorialSectorAgent(territorialSectorSvc.findById(3L)),
        new TerritorialSectorAgent(territorialSectorSvc.findById(4L))
    };

    terrirotialSectorAgentSvc.saveAll(Arrays.asList(territorialSectorAgents));
  }

  private void createMember() {

    //String name, String surname, DocType docType, String document
    Member[] members = {
        new Member("Marco", "Morana", DocType.DNI, "42078542"),
        new Member("Lucas", "Schneider", DocType.DNI, "42785274"),
        new Member("Agustin", "Bazzi", DocType.PASAPORTE, "317246387"),
        new Member("Julian", "Jose", DocType.DNI, "267213"),
        new Member("Julio", "Caesar", DocType.DNI, "37362231"),
        new Member("Bond", "James Bond", DocType.DNI, "82673932"),
        new Member("Sherlock", "Holmes", DocType.DNI, "1"),
        new Member("Watson", "Watson", DocType.DNI, "2"),
        new Member("Mycroft", "Holmes", DocType.DNI, "-1")
    };

    this.memberSvc.saveAll(Arrays.asList(members));
    // FALTA
    // AGREGARLE LOS JOURNEYS
    // UN PAR POR COMPARTIDO
    // CREAR CONTACTOS Y AGREGARSELOS
    // O8ST0
    // No falta nada, vos confia
  }

  private void createOrganization() {

    //public Organization(String socObj, Location locat, String clasific, OrgType type)
    Organization[] organizations = {
        new Organization("pepito srl", this.locationSvc.findById(4L), "A", OrgType.ONG),
        new Organization("capital maxima", this.locationSvc.findById(6L), "B", OrgType.COMPANY),
        new Organization("el gobierno", this.locationSvc.findById(9L), "C", OrgType.GOVERNMENTAL)
    };

    this.organizationSvc.saveAll(Arrays.asList(organizations));

  }

  private void createActivityData() {

    /*
    public ActivityData(
          ConsumptionType consumptionType,
          Double consumptionValue,
          PeriodicityFormat periodicityFormat,
          String periodicity
  )
     */
    ActivityData[] activityData = {
        new ActivityData(this.consumptionTypeSvc.findById(1L),
            7d, PeriodicityFormat.MMAAAA,
            "032022"),
        new ActivityData(this.consumptionTypeSvc.findById(2L),
            4.5, PeriodicityFormat.MMAAAA,
            "042022"),
        new ActivityData(this.consumptionTypeSvc.findById(3L),
            3.14, PeriodicityFormat.AAAA,
            "2022"),
        new ActivityData(this.consumptionTypeSvc.findById(4L),
            2.27, PeriodicityFormat.AAAA,
            "2021"),
        new ActivityData(this.consumptionTypeSvc.findById(5L),
            343d, PeriodicityFormat.MMAAAA,
            "052022"),
        new ActivityData(this.consumptionTypeSvc.findById(6L),
            73d, PeriodicityFormat.MMAAAA,
            "062022"),
        new ActivityData(this.consumptionTypeSvc.findById(7L),
            37d, PeriodicityFormat.MMAAAA,
            "072022"),
        new ActivityData(this.consumptionTypeSvc.findById(8L),
            11d, PeriodicityFormat.MMAAAA,
            "082022")
    };

    this.activityDataSvc.saveAll(Arrays.asList(activityData));
  }

  private void createUser() {
    try {
      User[] users = {
          new Registration()
              .setMember("Gonzalo", "Rodriguez Pardo", DocType.DNI, "42.877.601")
              .registerStandardUser("Pardios", "1Contra$enia"),
          new Registration()
              .setMember("Lucas", "Schneider", DocType.DNI, "42.396.327")
              .registerStandardUser("Pastita", "1Contra$enia"),
          new Registration()
              .setSectorAgent()
              .registerAgentUser("Agent_Smith", "1Contra$enia"),
          new Registration()
              .registerAdminUser("UltrAdmin", "1Contra$enia")
      };
      userSvc.saveAll(Arrays.asList(users));

    } catch (IOException ignored) {
    }
  }


  // Test random - dsps se borran o pasan a otro lugar

//  @Test
//  public void testDePruebaQueNOHaceNada() {
//    ConsumptionTypeRepo.getInstance().getAll().forEach(ct -> {
//      System.out.format("Id: %d\nNombre: %s\nUnidades: %s\nFE: %f\n\n",
//          ct.getId(),
//          ct.getName(),
//          UnitExpression.printUnits(ct.getUnits()),
//          ct.getEmissionFactor().getValue());
//    });
//
//    Assertions.assertTrue(true);
//  }
//
//  @Test
//  public void testGetAllUsers() {
//    List<User> users = UserRepo.getInstance().getAll();
//    Assertions.assertEquals(4, users.size());
//  }
//
//  @Test
//  public void testGetStandardUserByUsername() {
//    StandardUser lucas = (StandardUser) UserRepo.getInstance()
//        .getByCondition(Restrictions.eq("username", "Pastita"))
//        .get(0);
//    Assertions.assertEquals("Lucas", lucas.getMember().getName());
//  }
//
//  @Test
//  public void testGetAdministratorByUsername() {
//    Administrator admin = (Administrator) UserRepo.getInstance()
//        .getByCondition(Restrictions.eq("username", "UltrAdmin"))
//        .get(0);
//    Assertions.assertTrue(Arrays.stream(new int[]{1, 2, 3, 4}).anyMatch(id -> id == admin.getId()));
//  }

}
