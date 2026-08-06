package tablee.runtime.bytecode;

import tablee.runtime.exceptions.UnknownOpcodeError;

public enum Opcodes {

    CALL_FT((byte) 0), // CallFunctionalTable
    PUSH_CS((byte) 1); // PushConstant

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
