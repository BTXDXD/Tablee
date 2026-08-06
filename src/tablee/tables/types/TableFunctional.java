package tablee.tables.types;

import tablee.runtime.FunctionalNativeTable;
import tablee.tables.TableBase;
import tablee.tables.TableTypes;

import java.util.ArrayList;
import java.util.List;

public final class TableFunctional extends TableBase {

    private final List<TableVariable> args;
    private final FunctionalNativeTable functionalNativeTable;

    public TableFunctional(String name, List<TableVariable> args) {
        this(name, args, null);
    }

    public TableFunctional(String name, List<TableVariable> args, FunctionalNativeTable functionalNativeTable) {
        super(name, TableTypes.Functional);
        this.args = new ArrayList<>(args);
        this.functionalNativeTable = functionalNativeTable;
    }

    public List<TableVariable> getArgs() {
        return this.args;
    }

    public boolean hasArg(TableVariable arg) {
        return this.args.contains(arg);
    }

    public FunctionalNativeTable getFunctionalNativeTable() {
        return this.functionalNativeTable;
    }

    public boolean isNative() {
        return this.functionalNativeTable != null;
    }

    @Override
    public void delete() {
        this.args.clear();
        super.delete();
    }

}