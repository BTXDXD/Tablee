package tablee.runtime.bytecode;

import tablee.runtime.exceptions.UnknownOpcodeError;

public enum Opcodes {

    VaultAdd((byte) 0), // Object obj
    //1-4
    NewFunctionalTable((byte) 5), // String name, List<TableVariable> args
    NewTabularTable((byte) 6), // String name
    NewVariableTable((byte) 7), // String name, VariableTypes type, boolean isConst
    //8-9
    If((byte) 10), // Boolean logic
    Do((byte) 11), // Object obj
    LoopContinue((byte) 12),

    ;////////////////////////////////////

    private final byte code;
    private static final Opcodes[] BYTES_MAP = values();

    Opcodes(byte code) {
        this.code = code;
    }

    public static Opcodes fromByte(byte b) {
        if (b >= 0 && b < BYTES_MAP.length)
            return BYTES_MAP[b];
        throw new UnknownOpcodeError(b);
    }

    public byte getCode() {
        return this.code;
    }

}
