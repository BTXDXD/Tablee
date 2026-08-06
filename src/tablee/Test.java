package tablee;

import tablee.packages.stdlib.IOPackage;
import tablee.runtime.Engine;
import tablee.runtime.bytecode.Executor;
import tablee.runtime.bytecode.Opcodes;
import tablee.tables.types.TableFunctional;

public class Test {

    public static void main(String[] args) {
        new IOPackage();

        TableFunctional printlnFunctionalTable = Engine.getGlobalTable().findFunctionalTable("io.println");

        if (printlnFunctionalTable == null) {
            System.err.println("Ошибка: Функция io.println не найдена в globalTable!");
            return;
        }

        Object[] constantPool = new Object[] {
                "Привет!", printlnFunctionalTable
        };

        byte[] bytecode = new byte[] {
                Opcodes.PUSH_CS.getCode(), (byte) 0,
                Opcodes.CALL_FT.getCode(), (byte) 1
        };

        Executor executor = new Executor(bytecode, constantPool);
        executor.execute();
    }

}
