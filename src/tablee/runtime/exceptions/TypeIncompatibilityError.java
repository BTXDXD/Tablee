package tablee.runtime.exceptions;

import tablee.tables.VariableTypes;

public class TypeIncompatibilityError extends RuntimeException {

    public TypeIncompatibilityError(String variableName, VariableTypes variableType, String type) {
        super("A variable '" + variableName + "' of type " + variableType + " cannot take on values of type '" + type + "'");    }

}
