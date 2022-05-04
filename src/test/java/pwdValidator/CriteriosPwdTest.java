package pwdValidator;

import org.junit.jupiter.api.Test;
import passwordValidator.*;
import security.passwordValidator.*;
import security.passwordValidator.passwordValidator.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CriteriosPwdTest{

    // ---------- Longitud Minima ---------- //
    @Test
    public void longitudMinimaAceptaBien() {
        String aPassword = "menosD8";

        PasswordException thrown = assertThrows(
                PasswordException.class,
                () -> new ValidateMinLength().validatePassword(aPassword),
                "Esperaba que la validacion falle y no lo hizo."
        );

        assertTrue(thrown.getMessage().contains("The password must have 8 characters"));
    }

    // ---------- Peores 10k Passwords ---------- //
    @Test
    public void diccionarioRestringeBien(){
        String aPassword = "spiderman";

        PasswordException thrown = assertThrows(
                PasswordException.class,
                () -> new ValidateCommonPassword().validatePassword(aPassword),
                "Esperaba que la validacion falle y no lo hizo."
        );

        assertTrue(thrown.getMessage().contains("The password is not a safe password"));
    }


    @Test
    public void noTieneMayuscula(){
        String aPassword = "no tengo mayuscula";

        PasswordException thrown = assertThrows(
                PasswordException.class,
                () -> new ValidateCapitalLetter().validatePassword(aPassword),
                "Esperaba que la validacion falle y no lo hizo."
        );

        assertTrue(thrown.getMessage().contains("The password does not have a capital letter"));
    }


    @Test
    public void noTieneNumero(){
        String aPassword = "no tengo numero";

        PasswordException thrown = assertThrows(
                PasswordException.class,
                () -> new ValidateNumber().validatePassword(aPassword),
                "Esperaba que la validacion falle y no lo hizo."
        );

        assertTrue(thrown.getMessage().contains("The password must have a number"));
    }


    @Test
    public void noTieneCaracterEspecial(){
        String aPassword = "no tengo caracter especial";

        PasswordException thrown = assertThrows(
                PasswordException.class,
                () -> new ValidateSpecialChar().validatePassword(aPassword),
                "Esperaba que la validacion falle y no lo hizo."
        );

        assertTrue(thrown.getMessage().contains("The password must have a special character"));
    }



}
