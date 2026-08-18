package tablee.runtime.exceptions;

public class TableIndexOutOfBoundsError extends RuntimeException {

    public TableIndexOutOfBoundsError(String tableName, int index, int size) {
        super("Index " + index + " is out of bounds for table '" + tableName + "' (size: " + size + ")");
    }

}