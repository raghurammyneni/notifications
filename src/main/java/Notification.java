import java.util.ArrayList;
import java.util.List;

/**
 * Created by raghumyneni on 3/28/17.
 */
public class Notification {

    public enum NotificationType {
        COMMENT,
        LIKE,
        FRIEND_REQUEST,
        PRIVATE_MESSAGE
    }

    public enum ResourceType {
        PHOTO,
        LINK
    }

    List<Column> columns = new ArrayList<Column>();
    NotificationType type;

    public List<Column> getColumns() {
        return columns;
    }

    public NotificationType getType() {
        return type;
    }
}
