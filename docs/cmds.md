Simple Tables
    .isTabularTable()
    .isTemplateTable()
    .isVariableTable()
    .isFunctionalTable()
    .getTableName()
    .getParentTableName()
    .getMemoryWeight()
    .getLength()
    .isEmpty()
    .isLink()
    .getLink()
    .getCopyOf(table item)
    .get()

Tables < Simple Tables
    .contains(table item)

Tabular Tables < Tables
    .isExtendable()
    .isInherited()
    .isInheritable()
    .isInheritedBy(template item)

Extendable Tabular Tables < Tabular Tables
    .getByPos(integer pos);

Functional Tables < Tables
    .isNative()
    .getArgsCount()
    .hasArg(string arg)

Variable Tables < Simple Tables
    .getAsInteger()
    .getAsFloat()
    .getAsString()
    .getAsBoolean()
    .set(type value)
    .isConst()
    .isInteger()
    .isFloat()
    .isString()
    .isBoolean()
    .isNumber()