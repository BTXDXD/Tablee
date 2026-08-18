package tablee.tables;

public class TableBaseSimple {

    private final String name;
    private final TableTypes type;
    public TableBase parent;

    protected TableBaseSimple(String name, TableTypes type) {
        this.name = name;
        this.type = type;
    }

    public String getTableName() {
        return this.name;
    }

    public TableTypes getTableType() {
        return this.type;
    }

    public void delete() {
        if (this.parent != null) {
            this.parent.removeSubtable(this);
            this.parent = null;
        }
    }

}