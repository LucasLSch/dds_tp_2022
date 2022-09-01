package domain.contact;

import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.math.BigDecimal;

public class SMSNotificationMethod implements NotificationMethod {

  // Ejemplo con Twilio. Cada mensaje cuesta 1 dolar. Asi que borro mis credenciales por temas obvios :D
  // Find your Account Sid and Token at twilio.com/console
  public static final String ACCOUNT_SID = ""; // Habria que setearlo con los datos correspondientes, como variable del sistema
  public static final String AUTH_TOKEN = ""; // Same as above
  public static final String FROM_SMS = "MGf2040b81eb660a66826b4af78adba792"; // Lo mismo. Te lo da Twilio al registrarte

  @Override
  public void notify(Contact someContact, String someMessage) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    Message message = Message.creator(
            new com.twilio.type.PhoneNumber(someContact.getPhone()),
            FROM_SMS,
            someMessage)
        .create();

    System.out.println(message.getSid());
  }

}

