package tablee.tables.types;

import tablee.runtime.exceptions.TableIndexOutOfBoundsError;
import tablee.runtime.exceptions.TableTypeIncompatibilityError;
import tablee.tables.TableBase;
import tablee.tables.TableBaseSimple;
import tablee.tables.TableTypes;

public final class TableTabular extends TableBase {

    private final boolean isExtendable;
    private final TableTemplate originTemplate;
    private TableTemplate allowedTypeConstraint;

    public TableTabular(String name) {
        this(name, false, null);
    }

    public TableTabular(String name, boolean isExtendable) {
        this(name, isExtendable, null);
    }

    public TableTabular(String name, boolean isExtendable, TableTemplate originTemplate) {
        super(name, TableTypes.Tabular);
        this.isExtendable = isExtendable;
        this.originTemplate = originTemplate;
    }

    public boolean isExtendable() {
        return this.isExtendable;
    }

    public boolean isInherited() {
        return this.originTemplate != null;
    }

    public boolean isInheritedBy(TableTemplate template) {
        if (this.originTemplate == null || template == null) return false;
        return this.originTemplate.equals(template);
    }

    public TableBaseSimple getByPos(int pos) {
        if (pos < 0 || pos >= getSubtables().size()) {
            throw new TableIndexOutOfBoundsError(getTableName(), pos, getSubtables().size());
        }
        return getSubtables().get(pos);
    }

    public int getLength() {
        return getSubtables().size();
    }

    public boolean isEmpty() {
        return getSubtables().isEmpty();
    }

    public void setAllowedTypeConstraint(TableTemplate allowedTemplate) {
        this.allowedTypeConstraint = allowedTemplate;
    }

    @Override
    public void addSubtable(TableBaseSimple subtable) {
        if (allowedTypeConstraint != null) {
            if (subtable instanceof TableTabular tabular) {
                if (!tabular.isInheritedBy(allowedTypeConstraint)) {
                    String actualTemplateName = tabular.isInherited()
                            ? tabular.originTemplate.getTableName()
                            : tabular.getTableName();

                    throw new TableTypeIncompatibilityError(
                            getTableName(),
                            actualTemplateName,
                            allowedTypeConstraint.getTableName()
                    );
                }
            } else {
                throw new TableTypeIncompatibilityError(getTableName(), subtable.getTableType().name());
            }
        }

        super.addSubtable(subtable);
    }

}