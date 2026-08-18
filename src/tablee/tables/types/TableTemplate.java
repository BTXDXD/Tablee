package tablee.tables.types;

import tablee.tables.TableBase;
import tablee.tables.TableTypes;

public final class TableTemplate extends TableBase {

    private final boolean isExtendable;

    public TableTemplate(String name, boolean isExtendable) {
        super(name, TableTypes.Template);
        this.isExtendable = isExtendable;
    }

    public boolean isExtendable() {
        return this.isExtendable;
    }

    public TableTabular createInstance(String instanceName) {
        return new TableTabular(instanceName);
    }

}