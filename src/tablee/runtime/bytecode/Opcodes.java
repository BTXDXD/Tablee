package tablee.runtime.bytecode;

import tablee.runtime.exceptions.UnknownOpcodeError;

public enum Opcodes {

    Not((byte) 0),
    PushConstant((byte) 1),
    LoadConstant((byte) 2),
    Duplicate((byte) 3),
    Pop((byte) 4),

    NewTabularTable((byte) 5),
    NewTemplateTable((byte) 6),
    NewFunctionalTable((byte) 7),
    NewVariableTable((byte) 8),

    NewExtendableTabularTable((byte) 10),
    NewExtendableTemplateTable((byte) 11),
    NewConstantVariableTable((byte) 12),

    GetVariableTableValue((byte) 13),
    SetVariableTableValue((byte) 14),
    CallFunctionalTable((byte) 15),
    AddSubtable((byte) 16),
    EnterTable((byte) 17),
    LeaveTable((byte) 18),
    Return((byte) 19),

    Equal((byte) 20),   // ==
    Less((byte) 21),    // <
    Greater((byte) 22), // >

    JumpIfFalse((byte) 25),
    Jump((byte) 26),

    Add((byte) 30),      // +
    Subtract((byte) 31), // -
    Multiply((byte) 32), // *
    Divide((byte) 33),   // /
    Modulo((byte) 34),   // %
    ;

    private final byte code;
    private static final Opcodes[] BYTES_MAP = new Opcodes[256];

    static {
        for (Opcodes op : values())
            BYTES_MAP[op.code & 0xFF] = op;
    }

    Opcodes(byte code) {
        this.code = code;
    }

    public static Opcodes fromByte(byte b) {
        int index = b & 0xFF;
        Opcodes op = BYTES_MAP[index];
        if (op != null) return op;
        throw new UnknownOpcodeError(b);
    }

    public byte getCode() {
        return this.code;
    }

}
