/**
 * Created by raghumyneni on 3/28/17.
 */
public class Column {

    String columnFamily;
    String columnName;
    String value;

    public Column(String columnFamily, String columnName, String value) {
        this.columnFamily = columnFamily;
        this.columnName = columnName;
        this.value = value;
    }

    public String getColumnFamily() {
        return columnFamily;
    }

    public void setColumnFamily(String columnFamily) {
        this.columnFamily = columnFamily;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
