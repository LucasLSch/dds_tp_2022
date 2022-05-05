package passwordvalidatortest;

import org.junit.jupiter.api.Test;
import security.passwordvalidator.PasswordException;
import security.passwordvalidator.ValidateCapitalLetter;
import security.passwordvalidator.ValidateCommonPassword;
import security.passwordvalidator.ValidateMinLength;
import security.passwordvalidator.ValidateNumber;
import security.passwordvalidator.ValidateSpecialChar;


import static org.junit.jupiter.api.Assertions.*;

public class PasswordCriteriaTest {

  // --- Longitud Minima --- //

  @Test
  public void longitudMinimaRechazaBien() {
    String aPassword = "menosD8";

    PasswordException thrown = assertThrows(
        PasswordException.class,
        () -> new ValidateMinLength().validatePassword(aPassword),
        "Esperaba que la validacion falle y no lo hizo."
    );

    assertTrue(thrown.getMessage().contains("The password must have 8 characters"));
  }

  @Test
  public void longitudMinimaAceptaBien() {
    String aPassword = "tengo mas de 8 caracteres";
    assertDoesNotThrow(
        () -> new ValidateMinLength().validatePassword(aPassword)
    );
  }


  // --- Peores 10k Passwords --- //

  @Test
  public void passwordsComunesRechazaBien(){
    String aPassword = "spiderman";

    PasswordException thrown = assertThrows(
        PasswordException.class,
        () -> new ValidateCommonPassword().validatePassword(aPassword),
        "Esperaba que la validacion falle y no lo hizo."
    );

    assertTrue(thrown.getMessage().contains("The password is not a safe password"));
  }

  @Test
  public void passwordsComunesAceptaBien() {
    String aPassword = "no soy comun";
    assertDoesNotThrow(
        () -> new ValidateCommonPassword().validatePassword(aPassword)
    );
  }


  // --- Mayuscula Requerida --- //

  @Test
  public void mayusculaRequeridaRechazaBien(){
    String aPassword = "no tengo mayuscula";

    PasswordException thrown = assertThrows(
        PasswordException.class,
        () -> new ValidateCapitalLetter().validatePassword(aPassword),
        "Esperaba que la validacion falle y no lo hizo."
    );

    assertTrue(thrown.getMessage().contains("The password does not have a capital letter"));
  }

  @Test
  public void mayusculaRequeridaAceptaBien() {
    String aPassword = "tengo una Mayuscula";
    assertDoesNotThrow(
        () -> new ValidateCapitalLetter().validatePassword(aPassword)
    );
  }


  // --- Numero Requerido --- //

  @Test
  public void numeroRequeridoRechazaBien(){
    String aPassword = "no tengo numero";

    PasswordException thrown = assertThrows(
        PasswordException.class,
        () -> new ValidateNumber().validatePassword(aPassword),
        "Esperaba que la validacion falle y no lo hizo."
    );

    assertTrue(thrown.getMessage().contains("The password must have a number"));
  }

  @Test
  public void numeroRequeridoAceptaBien() {
    String aPassword = "tengo 1 numero";
    assertDoesNotThrow(
        () -> new ValidateNumber().validatePassword(aPassword)
    );
  }


  // --- Caracter Especial Requerido --- //

  @Test
  public void caracterEspecialRechazaBien(){
    String aPassword = "no tengo caracter especial";

    PasswordException thrown = assertThrows(
        PasswordException.class,
        () -> new ValidateSpecialChar().validatePassword(aPassword),
        "Esperaba que la validacion falle y no lo hizo."
    );

    assertTrue(thrown.getMessage().contains("The password must have a special character"));
  }

  @Test
  public void caracterEspecialAceptaBien() {
    String aPassword = "tengo caracter e$pecial";
    assertDoesNotThrow(
        () -> new ValidateSpecialChar().validatePassword(aPassword)
    );
  }

}
