package tablee.runtime.bytecode;

import tablee.tables.TableBase;
import tablee.tables.TableBaseSimple;
import tablee.tables.VariableTypes;
import tablee.tables.types.TableFunctional;
import tablee.tables.types.TableTabular;
import tablee.tables.types.TableTemplate;
import tablee.tables.types.TableVariable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Executor {

    private final byte[] bytecode;
    private final Object[] vault;
    private final Object[] stack = new Object[512];
    private int sp = 0;
    private TableBase currentTable;

    public Executor(byte[] bytecode, Object[] vault) {
        this.bytecode = bytecode;
        this.vault = vault;
    }

    public void execute() {
        int ip = 0;
        int vp = 0;

        final byte[] code = this.bytecode;
        final Object[] v = this.vault;
        final Object[] s = this.stack;

        while (ip < code.length) {
            Opcodes op = Opcodes.fromByte(code[ip++]);
            switch (op) {

                case Not -> {
                    s[sp - 1] = !((boolean) s[sp - 1]);
                }

                case PushConstant -> {
                    s[sp++] = v[vp++];
                }

                case LoadConstant -> {
                    int index = code[ip++] & 0xFF;
                    s[sp++] = v[index];
                }

                case Duplicate -> {
                    s[sp] = s[sp - 1];
                    sp++;
                }

                case Pop -> {
                    --sp;
                }

                case NewTabularTable -> {
                    String tableName = (String) v[vp++];
                    s[sp++] = new TableTabular(tableName);
                }

                case NewTemplateTable -> {
                    String tableName = (String) v[vp++];
                    s[sp++] = new TableTemplate(tableName, false);
                }

                case NewFunctionalTable -> {
                    String tableName = (String) v[vp++];
                    s[sp++] = new TableFunctional(tableName, (List<TableVariable>) v[vp++]);
                }

                case NewVariableTable -> {
                    String tableName = (String) v[vp++];
                    s[sp++] = new TableVariable(tableName, (VariableTypes) v[vp++], false);
                }

                case NewExtendableTabularTable -> {
                    String tableName = (String) v[vp++];
                    s[sp++] = new TableTabular(tableName, true);
                }

                case NewExtendableTemplateTable -> {
                    String tableName = (String) v[vp++];
                    s[sp++] = new TableTemplate(tableName, true);
                }

                case NewConstantVariableTable -> {
                    String tableName = (String) v[vp++];
                    s[sp++] = new TableVariable(tableName, (VariableTypes) v[vp++], true);
                }

                case GetVariableTableValue -> {
                    TableVariable var = (TableVariable) s[sp - 1];
                    s[sp - 1] = var.getValue();
                }

                case SetVariableTableValue -> {
                    Object value = s[--sp];
                    TableVariable var = (TableVariable) s[--sp];
                    var.setValue(value);
                }

                case CallFunctionalTable -> {
                    TableFunctional func = (TableFunctional) v[vp++];
                    int argsCount = func.getArgs().size();
                    List<Object> args = new ArrayList<>(argsCount);

                    for (int i = 0; i < argsCount; i++)
                        args.add(s[--sp]);

                    Collections.reverse(args);

                    if (func.isNative()) {
                        Object result = func.getFunctionalNativeTable().execute(args);
                        if (result != null)
                            s[sp++] = result;
                    }
                }

                case AddSubtable -> {
                    TableBaseSimple child = (TableBaseSimple) s[--sp];
                    TableBase parent = (TableBase) s[--sp];
                    parent.addSubtable(child);
                    s[sp++] = parent;
                }

                case EnterTable -> {
                    this.currentTable = (TableBase) s[--sp];
                }

                case LeaveTable -> {
                    if (this.currentTable.parent != null)
                        this.currentTable = this.currentTable.parent;
                }

                case Return -> {
                    return;
                }

                case Equal -> {
                    Object b = s[--sp];
                    Object a = s[--sp];
                    s[sp++] = Objects.equals(a, b);
                }

                case Less -> {
                    Number b = (Number) s[--sp];
                    Number a = (Number) s[--sp];
                    s[sp++] = (a instanceof Double || b instanceof Double)
                            ? a.doubleValue() < b.doubleValue()
                            : a.longValue() < b.longValue();
                }

                case Greater -> {
                    Number b = (Number) s[--sp];
                    Number a = (Number) s[--sp];
                    s[sp++] = (a instanceof Double || b instanceof Double)
                            ? a.doubleValue() > b.doubleValue()
                            : a.longValue() > b.longValue();
                }

                case JumpIfFalse -> {
                    int target = code[ip++] & 0xFF;
                    if (!((boolean) s[--sp]))
                        ip = target;
                }

                case Jump -> {
                    ip = code[ip++] & 0xFF;
                }

                case Add -> {
                    Object b = s[--sp];
                    Object a = s[--sp];
                    if (a instanceof String || b instanceof String) {
                        s[sp++] = String.valueOf(a) + b;
                    } else if (a instanceof Double || b instanceof Double) {
                        s[sp++] = ((Number) a).doubleValue() + ((Number) b).doubleValue();
                    } else {
                        s[sp++] = ((Number) a).longValue() + ((Number) b).longValue();
                    }
                }

                case Subtract -> {
                    Number b = (Number) s[--sp];
                    Number a = (Number) s[--sp];
                    if (a instanceof Double || b instanceof Double) {
                        s[sp++] = a.doubleValue() - b.doubleValue();
                    } else {
                        s[sp++] = a.longValue() - b.longValue();
                    }
                }

                case Multiply -> {
                    Number b = (Number) s[--sp];
                    Number a = (Number) s[--sp];
                    if (a instanceof Double || b instanceof Double) {
                        s[sp++] = a.doubleValue() * b.doubleValue();
                    } else {
                        s[sp++] = a.longValue() * b.longValue();
                    }
                }

                case Divide -> {
                    Number b = (Number) s[--sp];
                    Number a = (Number) s[--sp];
                    if (a instanceof Double || b instanceof Double) {
                        s[sp++] = a.doubleValue() / b.doubleValue();
                    } else {
                        s[sp++] = a.longValue() / b.longValue();
                    }
                }

                case Modulo -> {
                    Number b = (Number) s[--sp];
                    Number a = (Number) s[--sp];
                    if (a instanceof Double || b instanceof Double) {
                        s[sp++] = a.doubleValue() % b.doubleValue();
                    } else {
                        s[sp++] = a.longValue() % b.longValue();
                    }
                }

            }
        }
    }

}