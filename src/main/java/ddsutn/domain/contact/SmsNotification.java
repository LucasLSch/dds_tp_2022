package ddsutn.domain.contact;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class SmsNotification implements NotificationMethod {

  // Ejemplo con Twilio. Cada mensaje cuesta 1 dolar.
  // Asi que borro mis credenciales por temas obvios :D
  // Habria que setear los datos como variables del sistema
  // Find your Account Sid and Token at twilio.com/console
  public static final String ACCOUNT_SID = "";
  public static final String AUTH_TOKEN = "";
  public static final String FROM_SMS = "MGf2040b81eb660a66826b4af78adba792";

  @Override
  public void notify(Contact someContact, String someMessage) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    Message message = Message.creator(
            new com.twilio.type.PhoneNumber(someContact.getPhoneNumber()),
            FROM_SMS,
            someMessage)
        .create();

    System.out.println(message.getSid());
  }

}

