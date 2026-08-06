package tablee.runtime.exceptions;

public class UnknownOpcodeError extends RuntimeException {

    public UnknownOpcodeError(byte b) {
        super("Unknown opcode byte: " + b);
    }

}
