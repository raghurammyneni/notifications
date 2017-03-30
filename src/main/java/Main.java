import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by raghumyneni on 3/28/17.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Map<String,String> pm = new HashMap<String, String>();
        pm.put("from_user", "John");
        pm.put("for_user", "Trevor");
        pm.put("text", "How are you!");

        Map<String,String> fr = new HashMap<String, String>();
        fr.put("from_user", "Stephan");
        fr.put("for_user", "Trevor");

        Map<String,String> like = new HashMap<String, String>();
        like.put("from_user", "Tom");
        like.put("for_user", "Trevor");
        like.put("liked", "LINK");
        like.put("url", "#");

        Map<String,String> comment = new HashMap<String, String>();
        comment.put("from_user", "Seth");
        comment.put("for_user", "Trevor");
        comment.put("text", "Nice");
        comment.put("commented_on", "PHOTO");
        comment.put("url", "#");

        NotificationManager manager = new NotificationManager();
        List<Notification> notifications = new ArrayList<Notification>();

        notifications.add(manager.createNotification(Notification.NotificationType.PRIVATE_MESSAGE, pm));
        notifications.add(manager.createNotification(Notification.NotificationType.FRIEND_REQUEST, fr));
        notifications.add(manager.createNotification(Notification.NotificationType.LIKE, like));
        notifications.add(manager.createNotification(Notification.NotificationType.COMMENT, comment));


        for(Notification notification: notifications) {
            manager.addNotifictaion(notification);
        }



        List<Notification> treverNotifications = manager.getUserNotifications("Trevor");

        for(Notification notification: treverNotifications) {
            System.out.println(notification.toString());
        }


    }

}
