package tablee.tables;

import tablee.tables.types.TableFunctional;

import java.util.ArrayList;
import java.util.List;

public class TableBase extends TableBaseSimple {

    private final ArrayList<TableBaseSimple> subtables;

    protected TableBase(String name, TableTypes type) {
        super(name, type);
        this.subtables = new ArrayList<>();
    }

    public TableFunctional findFunctionalTable(String name) {
        if (name == null || name.isBlank()) return null;

        if (name.contains(".")) {
            String[] parts = name.split("\\.");
            String pkgName = parts[0];
            String functionalTableName = parts[1];

            TableBaseSimple sub = findSubtable(pkgName);

            if (sub instanceof TableBase table)
                return table.findFunctionalTable(functionalTableName);
            return null;
        }

        for (TableBaseSimple sub : subtables) {
            if (sub instanceof TableFunctional functional && sub.getTableName().equals(name)) {
                return functional;
            }
        }

        if (parent != null)
            return parent.findFunctionalTable(name);

        return null;
    }

    public TableBaseSimple findSubtable(String name) {
        for (TableBaseSimple sub : subtables) {
            if (sub.getTableName().equals(name))
                return sub;
        }
        return null;
    }

    public ArrayList<TableBaseSimple> getSubtables() {
        return this.subtables;
    }

    public boolean hasSubtable(TableBaseSimple name) {
        return this.subtables.contains(name);
    }

    public void addSubtable(TableBaseSimple subtable) {
        subtable.parent = this;
        this.subtables.add(subtable);
    }

    public void removeSubtable(TableBaseSimple subtable) {
        this.subtables.remove(subtable);
    }

    @Override
    public void delete() {
        List<TableBaseSimple> subtableCopy = new ArrayList<>(this.subtables);
        for (TableBaseSimple subtable : subtableCopy) {
            subtable.delete();
        }
        this.subtables.clear();
        super.delete();
    }

}