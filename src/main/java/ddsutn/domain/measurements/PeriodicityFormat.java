package ddsutn.domain.measurements;

import ddsutn.domain.exceptions.IncorrectInputException;

import java.time.LocalDate;
import java.util.Locale;

public enum PeriodicityFormat {
  MMAAAA {
    public LocalDate getDate(String periodicityString) {
      return LocalDate.of(
          Integer.parseInt(periodicityString.substring(2, 6)),
          Integer.parseInt(periodicityString.substring(0, 2)),
          1);
    }
  },
  AAAA {
    public LocalDate getDate(String periodicityString) {
      return LocalDate.of(
          Integer.parseInt(periodicityString),
          1,
          1);
    }
  };

  public static void validatePeriodicity(String periodicity, PeriodicityFormat pFormat) {
    Integer length = periodicity.length();
    if (!length.equals(4) && !length.equals(6)) {
      throw new IncorrectInputException(periodicity);
    }
    if (length.equals(4)) {
      pFormat.validateDate(periodicity);
    }
    if (length.equals(6)) {
      pFormat.validateDate(periodicity);
    }
  }

  public void validateDate(String periodicity) {
    boolean greaterThanNow = this.getDate(periodicity).isAfter(LocalDate.now());
    if (greaterThanNow) {
      throw new IncorrectInputException(periodicity);
    }
  }

  public abstract LocalDate getDate(String abc);

  public static PeriodicityFormat periodicityValueOf(String period) {
    if (period.toLowerCase(Locale.ROOT).equals("mensual")) {
      return PeriodicityFormat.MMAAAA;
    }
    if (period.toLowerCase(Locale.ROOT).equals("anual")) {
      return PeriodicityFormat.AAAA;
    }
    throw new IncorrectInputException(period);
  }
}
