
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.*;

/**
 * Created by raghumyneni on 3/28/17.
 */
public class NotificationManager {

    public Notification createNotification (Notification.NotificationType type,
                                            Map<String, String> parameters) {
        switch (type) {
            case LIKE:
                return new LikeNotification(parameters.get("for_user"),
                        parameters.get("from_user"),
                        Notification.ResourceType.valueOf(parameters.get("liked")),
                        parameters.get("url"));

            case COMMENT:
                return new CommentNotification(parameters.get("for_user"),
                        parameters.get("from_user"),
                        parameters.get("text"),
                        Notification.ResourceType.valueOf(parameters.get("commented_on")),
                        parameters.get("url"));

            case FRIEND_REQUEST:
                return new FriendRequestNotification(parameters.get("for_user"), parameters.get("from_user"));

            case PRIVATE_MESSAGE:
                return new PrivateMessageNotification(parameters.get("for_user"),
                        parameters.get("from_user"),
                        parameters.get("text"));

            default:
                return new Notification();
        }
    }

    public void addNotifictaion (Notification notification) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(conf);

        Table table = null;
        try {
            table = connection.getTable(TableName.valueOf("notifications"));

            String uniqueID = UUID.randomUUID().toString();
            Put put = new Put(Bytes.toBytes(uniqueID));

            List<Column> columns = notification.getColumns();
            for(Column col: columns) {
                put.addColumn(Bytes.toBytes(col.getColumnFamily()),
                        Bytes.toBytes(col.getColumnName()),
                        Bytes.toBytes(col.getValue()));
            }
            table.put(put);
        } finally {
            connection.close();
            if(table != null) {
                table.close();
            }
        }
    }

    public List<Notification> getUserNotifications(String user) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(conf);

        List<Notification> userNotifications = new ArrayList<Notification>();
        Table table = null;
        ResultScanner results = null;
        try {
            table = connection.getTable(TableName.valueOf("notifications"));

            Scan scan = new Scan();
            SingleColumnValueFilter filter = new SingleColumnValueFilter(
                    Bytes.toBytes("attributes"),
                    Bytes.toBytes("for_user"),
                    CompareFilter.CompareOp.EQUAL,
                    new SubstringComparator(user));
            filter.setFilterIfMissing(true);
            scan.setFilter(filter);
            scan.addFamily(Bytes.toBytes("attributes"));

            results = table.getScanner(scan);

            for (Result res : results) {
                Notification.NotificationType type = Notification.NotificationType.valueOf(
                        Bytes.toString(res.getValue(Bytes.toBytes("attributes"), Bytes.toBytes("type"))));
                Map<String,String> parameters = parseResults(res);
                userNotifications.add(createNotification(type, parameters));
            }

        } finally {
            connection.close();
            if(table != null) {
                table.close();
            }
            if(results != null) {
                results.close();
            }
        }

        return userNotifications;
    }

    private Map<String, String> parseResults (Result result) {

        Map<String, String> parameters = new HashMap<String, String>();

        for(Cell cell: result.listCells()) {
            if(CellUtil.cloneQualifier(cell).toString() != "type") {
                parameters.put(new String(CellUtil.cloneQualifier(cell)), new String(CellUtil.cloneValue(cell)));
            }
        }
        return  parameters;
    }

}
