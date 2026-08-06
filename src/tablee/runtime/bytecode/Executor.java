package tablee.runtime.bytecode;

import tablee.tables.types.TableFunctional;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Executor {

    private final byte[] bytecode;
    private final Object[] constantPool;
    private final Deque<Object> valueStack = new ArrayDeque<>();

    // bytecode (byte[]): Массив чисел. Это инструкция для процессора в виде байтов (например, [1, 0, 0, 1]).
    // constantPool (Object[]): «Склад» сложных объектов. Байт-код хранит только маленькие индексы (например, 0 или 1), а на «складе» под этим индексом лежат реальные строки ("Привет!") или объекты функций (TableFunctional).
    // valueStack (Deque<Object>): Стек вычислений. Место в памяти, куда команда положит свои результаты, а следующая команда заберет их оттуда.

    public Executor(byte[] bytecode, Object[] constantPool) {
        this.bytecode = bytecode;
        this.constantPool = constantPool;
    }

    public void execute() {
        int ip = 0; // Instruction Pointer

        while (ip < bytecode.length) {
            Opcodes op = Opcodes.fromByte(bytecode[ip++]);
            switch (op) {
                case CALL_FT -> {
                    int functionalTableIndex = bytecode[ip++];
                    TableFunctional functionalTable = (TableFunctional) constantPool[functionalTableIndex];
                    int argCount = functionalTable.getArgs().size();
                    List<Object> args = new ArrayList<>();

                    for (int i = 0; i < argCount; i++)
                        args.add(0, valueStack.pop());

                    if (functionalTable.isNative()) functionalTable.getFunctionalNativeTable().execute(args);
                }
                case PUSH_CS -> {
                    int constIndex = bytecode[ip++];
                    valueStack.push(constantPool[constIndex]);
                }
            }
        }
    }

}
