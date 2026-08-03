package tablee.tables.types;

import tablee.tables.TableBaseSimple;
import tablee.tables.TableTypes;

public final class TableVariable extends TableBaseSimple {

    private boolean value;

    public TableVariable(String name) {
        super(name, TableTypes.VARIABLE);
    }

}