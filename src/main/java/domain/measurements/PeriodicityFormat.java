package domain.measurements;

import java.time.LocalDate;

public enum PeriodicityFormat {
  MMAAAA {
    public LocalDate getDate(String periodicityString) {
      return LocalDate.of(
          Integer.getInteger(periodicityString.substring(2, 5)),
          Integer.getInteger(periodicityString.substring(0, 1)),
          1);
    }
  },
  AAAA {
    public LocalDate getDate(String periodicityString) {
      return LocalDate.of(
          Integer.getInteger(periodicityString.substring(0, 3)),
          1,
          1);
    }
  };

  public abstract LocalDate getDate(String abc);
}
