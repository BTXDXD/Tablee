package tablee.packages;

import tablee.tables.types.TableTabular;

public class PackageBase {

    private final TableTabular packageRootTable;

    public PackageBase(String name) {
        this.packageRootTable = new TableTabular(name);
    }

}