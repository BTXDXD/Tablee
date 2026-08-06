package tablee.tables.types;

import tablee.runtime.exceptions.ConstModificationError;
import tablee.runtime.exceptions.TypeIncompatibilityError;
import tablee.tables.TableBaseSimple;
import tablee.tables.TableTypes;
import tablee.tables.VariableTypes;

public final class TableVariable extends TableBaseSimple {

    private final VariableTypes type;
    public final boolean isConst;
    private Object value;

    public TableVariable(String name, VariableTypes type, boolean isConst) {
        super(name, TableTypes.Variable);
        this.type = type;
        this.isConst = isConst;
        setBaseValue();
    }

    public void setValue(Object value) {
        if (isConst) throw new ConstModificationError(this.getTableName());
        if (!this.type.isCompatible(value)) {
            String valueTypeName = (value != null) ? value.getClass().getSimpleName() : "null";

            throw new TypeIncompatibilityError(getTableName(), this.type, valueTypeName);
        }

        this.value = value;
    }

    private void setBaseValue() {
        this.value = switch (this.type) {
            case Integer -> 0L;
            case Float -> 0.0d;
            case String -> "";
            case Boolean -> false;
        };
    }

    public Object getValue() {
        return this.value;
    }

    public VariableTypes getType() {
        return this.type;
    }

    public boolean isConst() {
        return this.isConst;
    }

}