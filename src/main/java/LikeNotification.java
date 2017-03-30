/**
 * Created by raghumyneni on 3/28/17.
 */
public class LikeNotification extends Notification {

    NotificationType type = NotificationType.LIKE;

    LikeNotification(String user, String fromUser,
                     ResourceType likedRes, String likedURL) {
        this.columns.add(new Column("attributes", "type", this.type.toString()));
        this.columns.add(new Column("attributes", "for_user", user));
        this.columns.add(new Column("attributes", "from_user", fromUser));
        this.columns.add(new Column("attributes", "liked", likedRes.toString()));
        this.columns.add(new Column("attributes", "url", likedURL));
    }

    public String toString() {
        return this.columns.get(2).value + " likes your " + this.columns.get(3).value;
    }

}
