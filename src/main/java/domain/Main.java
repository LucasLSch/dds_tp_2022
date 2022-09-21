package domain;

import domain.measurements.CarbonFootprint;
import domain.measurements.unit.BaseUnit;
import domain.measurements.unit.Proportionality;
import domain.measurements.unit.Unit;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import repositories.CarbonFootprintRepo;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {

    private void metodo() {

        Set<Unit> unitSet = new HashSet<>();
        unitSet.add(new Unit(BaseUnit.METER, 3, Proportionality.DIRECT));
        unitSet.add(new Unit(BaseUnit.SECOND, 0, Proportionality.INVERSE));

        CarbonFootprint miCf = new CarbonFootprint(70.0, unitSet, LocalDate.now());

        /*
        withTransaction(() -> {
            entityManager().persist(miCf);
        });*/

        //CarbonFootprintRepo.getInstance().save(miCf);
        //List<CarbonFootprint> listaCF = CarbonFootprintRepo.getInstance().getAll();
        //listaCF.forEach( cf -> System.out.format("Id: %d, value: %f%n", cf.getId(), cf.getValue()));


    }

    public static void main(String[] args) {
        new Main().metodo();
        return;
    }
}
