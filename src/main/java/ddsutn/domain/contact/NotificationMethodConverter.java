package ddsutn.domain.contact;

import java.util.Arrays;
import javax.persistence.AttributeConverter;

public class NotificationMethodConverter implements AttributeConverter<NotificationMethod, String> {

  @Override
  public String convertToDatabaseColumn(NotificationMethod method) {
    return Arrays.stream(method.getClass().getSimpleName().split("Notification", 2))
            .findFirst()
            .orElse("undefined!")
            .toUpperCase();
  }

  @Override
  public NotificationMethod convertToEntityAttribute(String dbData) {
    switch (dbData) {
      case "SMS":
        return new SmsNotification();
      case "WPP":
        return new WppNotification();
      case "EMAIL":
        return new EmailNotification();
      default:
        return null;
      //TODO exception?
    }
  }
}
