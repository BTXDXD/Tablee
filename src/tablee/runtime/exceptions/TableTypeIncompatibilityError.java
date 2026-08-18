package tablee.runtime.exceptions;

public class TableTypeIncompatibilityError extends RuntimeException {

    public TableTypeIncompatibilityError(String targetTableName, String actualTypeName, String expectedTypeName) {
        super("Cannot add element of type '" + actualTypeName + "' to typed tabular '" + targetTableName + "' (expected '" + expectedTypeName + "')");
    }

    public TableTypeIncompatibilityError(String targetTableName, String actualTypeName) {
        super("Incompatible element type '" + actualTypeName + "' for typed tabular '" + targetTableName + "'");
    }

}