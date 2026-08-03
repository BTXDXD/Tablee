package tablee.tables.types;

import tablee.tables.TableBase;
import tablee.tables.TableTypes;

import java.util.ArrayList;

public final class TableFunctional extends TableBase {

    private final ArrayList<TableVariable> args;

    public TableFunctional(String name, ArrayList<TableVariable> args) {
        super(name, TableTypes.FUNCTIONAL);
        this.args = new ArrayList<>(args);
    }

    public ArrayList<TableVariable> getArgs() {
        return this.args;
    }

    public boolean hasArg(TableVariable arg) {
        return this.args.contains(arg);
    }

    @Override
    public void delete() {
        this.args.clear();
        super.delete();
    }

}