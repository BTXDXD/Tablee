package tablee.tables;

import java.util.ArrayList;
import java.util.List;

public class TableBase extends TableBaseSimple {

    private final ArrayList<TableBaseSimple> subtables;

    protected TableBase(String name, TableTypes type) {
        super(name, type);
        this.subtables = new ArrayList<>();
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