package ddsutn.services;

import ddsutn.domain.exceptions.IncorrectInputException;
import ddsutn.domain.measurements.ActivityData;
import ddsutn.domain.measurements.ConsumptionType;
import ddsutn.domain.measurements.PeriodicityFormat;
import ddsutn.domain.organization.Organization;
import ddsutn.repositories.ConsumptionTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class ConsumptionTypeSvc extends GenericSvcImpl<ConsumptionType, Long> {

  @Autowired
  private ConsumptionTypeRepo consumptionTypeRepo;

  @Override
  public CrudRepository<ConsumptionType, Long> getRepo() {
    return this.consumptionTypeRepo;
  }

  public ConsumptionType findByName(String name) {
    List<ConsumptionType> allCts = this.findAllByCondition(ct ->
            ct.getName().replace(" ", "").toLowerCase(Locale.ROOT)
                    .equals(name.replace(" ", "").toLowerCase(Locale.ROOT)));

    return allCts.stream().findFirst().orElse(null);
  }

  public void parseLine(Organization read, String line) {
    String[] format = line.split(",");

    try {
      ConsumptionType ct = this.findByName(format[0].trim());
      String periodicity = format[3].replace("/", "").trim();
      PeriodicityFormat pFormat = PeriodicityFormat.periodicityValueOf(format[2].trim());
      PeriodicityFormat.validatePeriodicity(periodicity, pFormat);
      read.addActivityData(new ActivityData(
              ct,
              Double.parseDouble(format[1].trim()),
              pFormat,
              periodicity
      ));
    } catch (Exception ignored) {
      throw new IncorrectInputException(line);
    }
  }
}
