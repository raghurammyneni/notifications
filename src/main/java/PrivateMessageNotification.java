/**
 * Created by raghumyneni on 3/28/17.
 */
public class PrivateMessageNotification extends Notification {

    NotificationType type = NotificationType.PRIVATE_MESSAGE;

    PrivateMessageNotification(String user, String fromUser, String messageText) {
        this.columns.add(new Column("attributes", "type", this.type.toString()));
        this.columns.add(new Column("attributes", "for_user", user));
        this.columns.add(new Column("attributes", "from_user", fromUser));
        this.columns.add(new Column("attributes", "text", messageText));
    }

    public String toString() {
        return this.columns.get(2).value + " sent you a message " + this.columns.get(3).value;
    }

}
