package domainTests;

import ddsutn.domain.exceptions.IncorrectInputException;
import ddsutn.domain.measurements.PeriodicityFormat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.LocalDate;

public class PeriodicityFormatTest {

  @Test
  public void canGetPeriodicityValueFromWords() {
    PeriodicityFormat year = PeriodicityFormat.periodicityValueOf("anual");
    PeriodicityFormat month = PeriodicityFormat.periodicityValueOf("mensual");

    Assertions.assertEquals(PeriodicityFormat.AAAA, year);
    Assertions.assertEquals(PeriodicityFormat.MMAAAA, month);
  }

  @Test
  public void givenAnInvalidValueForPeriodicityThrowsAnException() {
    IncorrectInputException thrown = Assertions.assertThrows(
            IncorrectInputException.class,
            () -> PeriodicityFormat.periodicityValueOf("valor invalido")
    );

    Assertions.assertEquals(thrown.getMessage(), "valor invalido input value is not valid");
  }

  @Test
  public void getDateFromPeriodicityFormat() {
    String yearFormat = "2022";
    String monthFormat = "122022";

    LocalDate yearDate = PeriodicityFormat.AAAA.getDate(yearFormat);
    LocalDate monthDate = PeriodicityFormat.MMAAAA.getDate(monthFormat);

    Assertions.assertEquals(LocalDate.of(2022, 1, 1), yearDate);
    Assertions.assertEquals(LocalDate.of(2022, 12, 1), monthDate);
  }

  @Test
  public void canValidateAPeriodicityFormat() {
    String notValidYear = "2023";
    String notValidMonth = "122023";

    IncorrectInputException thrownYear = Assertions.assertThrows(
            IncorrectInputException.class,
            () -> PeriodicityFormat.validatePeriodicity(notValidYear, PeriodicityFormat.AAAA)
    );

    IncorrectInputException thrownMonth = Assertions.assertThrows(
            IncorrectInputException.class,
            () -> PeriodicityFormat.validatePeriodicity(notValidMonth, PeriodicityFormat.MMAAAA)
    );

    Assertions.assertThrows(
            DateTimeException.class,
            () -> PeriodicityFormat.validatePeriodicity("132022", PeriodicityFormat.MMAAAA)
    );

    Assertions.assertThrows(
            NumberFormatException.class,
            () -> PeriodicityFormat.validatePeriodicity("2o22", PeriodicityFormat.AAAA)
    );

    Assertions.assertEquals(thrownYear.getMessage(), "2023 input value is not valid");
    Assertions.assertEquals(thrownMonth.getMessage(), "122023 input value is not valid");
  }
}
