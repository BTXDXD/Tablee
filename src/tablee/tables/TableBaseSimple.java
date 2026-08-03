package tablee.tables;

public class TableBaseSimple {

    private final String name;
    private final TableTypes type;
    TableBase parent;

    protected TableBaseSimple(String name, TableTypes type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public TableTypes getType() {
        return this.type;
    }

    public void delete() {
        if (this.parent != null) {
            this.parent.removeSubtable(this);
            this.parent = null;
        }
    }

}