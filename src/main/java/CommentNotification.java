/**
 * Created by raghumyneni on 3/28/17.
 */
public class CommentNotification extends Notification {

    NotificationType type = NotificationType.COMMENT;

    CommentNotification(String user, String fromUser, String commentText,
                        ResourceType commentedOn, String commentedOnURL) {
        this.columns.add(new Column("attributes", "type", this.type.toString()));
        this.columns.add(new Column("attributes", "for_user", user));
        this.columns.add(new Column("attributes", "from_user", fromUser));
        this.columns.add(new Column("attributes", "commented_on", commentedOn.toString()));
        this.columns.add(new Column("attributes", "text", commentText));
        this.columns.add(new Column("attributes", "url", commentedOnURL));
    }

    public String toString() {
        return this.columns.get(2).value + " commented on your " + this.columns.get(3).value
                + " - " + this.columns.get(4).value + "";
    }

}
