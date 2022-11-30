package ddsutn.component;

import ddsutn.domain.measurements.ConsumptionType;
import ddsutn.domain.measurements.EmissionFactor;
import ddsutn.domain.measurements.unit.BaseUnit;
import ddsutn.domain.measurements.unit.Proportionality;
import ddsutn.domain.measurements.unit.Unit;
import ddsutn.services.ConsumptionTypeSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class ConsumptionTypeLoader implements ApplicationRunner {

  @Autowired
  private ConsumptionTypeSvc consumptionTypeSvc;

  private static Unit kilogram(Proportionality prop) {
    return new Unit(BaseUnit.GRAM, 3, prop);
  }

  // Units utils

  private static Unit cubicMeter(Proportionality prop) {
    return new Unit(BaseUnit.LITER, 3, prop);
  }

  private static Unit liter(Proportionality prop) {
    return new Unit(BaseUnit.LITER, 0, prop);
  }

  private static Unit kilometer(Proportionality prop) {
    return new Unit(BaseUnit.METER, 3, prop);
  }

  private static Unit hour(Proportionality prop) {
    return new Unit(BaseUnit.HOUR, 0, prop);
  }

  private static Unit kilowatt(Proportionality prop) {
    return new Unit(BaseUnit.WATT, 3, prop);
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {

    if (!this.consumptionTypeSvc.findAll().isEmpty()) {
      return;
    }

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
        hour(Proportionality.DIRECT),
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
                hour(Proportionality.INVERSE))),
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
}
