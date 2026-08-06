package tablee.runtime.exceptions;

public class ConstModificationError extends RuntimeException {

    public ConstModificationError(String variableName) {
        super("It is not possible to change the value of the constant '" + variableName + "'");
    }

}
