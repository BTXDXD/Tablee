package tablee;

import tablee.packages.stdlib.IOPackage;
import tablee.runtime.bytecode.Executor;

public class Test {

    public static void main(String[] args) {
        new IOPackage();

        Object[] vault = new Object[] {
        };

        byte[] bytecode = new byte[] {

        };

        System.out.println("Запуск виртуальной машины Tablee...");
        Executor executor = new Executor(bytecode, vault);
        executor.execute();
    }

}