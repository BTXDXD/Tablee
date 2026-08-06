package tablee.tables;

public enum VariableTypes {

    Integer,
    Float,
    String,
    Boolean;

    public boolean isCompatible(Object value) {
        if (value == null) return false;

        return switch (this) {
            case Integer -> value instanceof Long;
            case Float -> value instanceof Double;
            case String -> value instanceof String;
            case Boolean -> value instanceof Boolean;
        };
    }

}