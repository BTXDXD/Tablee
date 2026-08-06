package tablee.packages;

import tablee.runtime.Engine;
import tablee.tables.types.TableTabular;

public abstract class PackageBase {

    private final TableTabular packageRootTable;

    public PackageBase(String name) {
        this.packageRootTable = new TableTabular(name);
    }

    protected abstract void setupPackage();

    public TableTabular getPackageRootTable() {
        return packageRootTable;
    }

    public void register() {
        setupPackage();
        Engine.registerPackage(this);
    }

}