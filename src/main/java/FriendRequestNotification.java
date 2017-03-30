/**
 * Created by raghumyneni on 3/28/17.
 */
public class FriendRequestNotification extends Notification {

    NotificationType type = NotificationType.FRIEND_REQUEST;

    FriendRequestNotification(String user, String fromUser) {
        this.columns.add(new Column("attributes", "type", this.type.toString()));
        this.columns.add(new Column("attributes", "for_user", user));
        this.columns.add(new Column("attributes", "from_user", fromUser));
    }

    public String toString() {
        return "You have friend request from " + this.columns.get(2).value;
    }

}
