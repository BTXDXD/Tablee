package tablee.runtime.bytecode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Executor {

    private final byte[] bytecode;
    private final Object[] vault;
    private final Deque<Object> valueStack = new ArrayDeque<>();

    // bytecode (byte[]): Массив чисел. Это инструкция для процессора в виде байтов (например, [1, 0, 0, 1]).
    // vault (Object[]): «Склад» сложных объектов. Байт-код хранит только маленькие индексы (например, 0 или 1), а на «складе» под этим индексом лежат реальные строки ("Привет!") или объекты функций (TableFunctional).
    // valueStack (Deque<Object>): Стек вычислений. Место в памяти, куда команда положит свои результаты, а следующая команда заберет их оттуда.

    public Executor(byte[] bytecode, Object[] vault) {
        this.bytecode = bytecode;
        this.vault = vault;
    }

    public void execute() {
        int ip = 0; // Instruction Pointer

        while (ip < bytecode.length) {
            Opcodes op = Opcodes.fromByte(bytecode[ip++]);
            switch (op) {

            }
        }
    }

}
