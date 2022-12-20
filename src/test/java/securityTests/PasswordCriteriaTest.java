package securityTests;

import ddsutn.security.passwordvalidator.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordCriteriaTest {

  // --- Longitud Minima --- //

  @Test
  public void longitudMinimaRechazaBien() {
    String somePassword = "menosD8";

    PasswordException thrown = assertThrows(
        PasswordException.class,
        () -> new ValidateMinLength().validatePassword(somePassword),
        "Esperaba que la validacion falle y no lo hizo."
    );

    assertTrue(thrown.getMessage().contains("Debe tener al menos 8 caracteres"));
  }

  @Test
  public void longitudMinimaAceptaBien() {
    String somePassword = "tengo mas de 8 caracteres";
    assertDoesNotThrow(
        () -> new ValidateMinLength().validatePassword(somePassword)
    );
  }


  // --- Peores 10k Passwords --- //

  @Test
  public void passwordsComunesRechazaBien() {
    String somePassword = "spiderman";

    PasswordException thrown = assertThrows(
        PasswordException.class,
        () -> new ValidateCommonPassword().validatePassword(somePassword),
        "Esperaba que la validacion falle y no lo hizo."
    );

    assertTrue(thrown.getMessage().contains("La contraseña es muy común"));
  }

  @Test
  public void passwordsComunesAceptaBien() {
    String somePassword = "no soy comun";
    assertDoesNotThrow(
        () -> new ValidateCommonPassword().validatePassword(somePassword)
    );
  }


  // --- Mayuscula Requerida --- //

  @Test
  public void mayusculaRequeridaRechazaBien() {
    String somePassword = "no tengo mayuscula";

    PasswordException thrown = assertThrows(
        PasswordException.class,
        () -> new ValidateCapitalLetter().validatePassword(somePassword),
        "Esperaba que la validacion falle y no lo hizo."
    );

    assertTrue(thrown.getMessage().contains("Debe tener por lo menos una mayúscula"));
  }

  @Test
  public void mayusculaRequeridaAceptaBien() {
    String somePassword = "tengo una Mayuscula";
    assertDoesNotThrow(
        () -> new ValidateCapitalLetter().validatePassword(somePassword)
    );
  }


  // --- Numero Requerido --- //

  @Test
  public void numeroRequeridoRechazaBien() {
    String somePassword = "no tengo numero";

    PasswordException thrown = assertThrows(
        PasswordException.class,
        () -> new ValidateNumber().validatePassword(somePassword),
        "Esperaba que la validacion falle y no lo hizo."
    );

    assertTrue(thrown.getMessage().contains("Debe tener algún número"));
  }

  @Test
  public void numeroRequeridoAceptaBien() {
    String somePassword = "tengo 1 numero";
    assertDoesNotThrow(
        () -> new ValidateNumber().validatePassword(somePassword)
    );
  }


  // --- Caracter Especial Requerido --- //

  @Test
  public void caracterEspecialRechazaBien() {
    String somePassword = "no tengo caracter especial";

    PasswordException thrown = assertThrows(
        PasswordException.class,
        () -> new ValidateSpecialChar().validatePassword(somePassword),
        "Esperaba que la validacion falle y no lo hizo."
    );

    assertTrue(thrown.getMessage().contains("Debe tener al menos un caracter especial"));
  }

  @Test
  public void caracterEspecialAceptaBien() {
    String somePassword = "tengo caracter e$pecial";
    assertDoesNotThrow(
        () -> new ValidateSpecialChar().validatePassword(somePassword)
    );
  }

}
