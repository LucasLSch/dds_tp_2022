package domain.contact;

public enum NotificationMethodType implements NotificationMethod {
    EMAIL (new EmailNotification()),
    SMS (new SmsNotification()),
    WPP (new WppNotification());

    private final NotificationMethod method;

    private NotificationMethodType(NotificationMethod method) {
        this.method = method;
    }

    public void notify(Contact someContact, String someMessage) {
        this.method.notify(someContact, someMessage);
    }
}
