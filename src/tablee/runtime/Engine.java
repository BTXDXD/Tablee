package tablee.runtime;

import tablee.packages.PackageBase;
import tablee.runtime.bytecode.Executor;
import tablee.runtime.exceptions.FileNotFoundError;
import tablee.runtime.exceptions.FileReadError;
import tablee.runtime.exceptions.InvalidFilePathError;
import tablee.tables.types.TableTabular;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;

public final class Engine {

    public static boolean tableeDebugMode = false;

    private static Consumer<String> errorHandler = System.err::print;
    private static Consumer<String> outputHandler = System.out::print;

    private static final TableTabular globalTable = new TableTabular("Global");

    private Engine() {}

    public static void registerPackage(PackageBase pkg) {
        globalTable.addSubtable(pkg.getPackageRootTable());
    }

    public static void addFile(String filePath) {
        runSafely(() -> {
            String tableName = convertFilePathToTableName(filePath);
            String code = readFileContent(filePath);

            addTable(tableName, code);
        });
    }

    public static void addCode(String tableName, String code) {
        runSafely(() -> addTable(tableName, code));
    }

    public static TableTabular getGlobalTable() {
        return globalTable;
    }

    private static void addTable(String tableName, String code) {
        TableTabular newTable = new TableTabular(tableName);
        globalTable.addSubtable(newTable);
        // parse(newTable) -> convertToBytecode() :ret List<Opcodes>
        byte[] bytecode = new byte[0];
        Object[] constantPool = new Object[0];
        Executor executor = new Executor(bytecode, constantPool);
        executor.execute();
    }

    private static String convertFilePathToTableName(String filePath) {
        if (filePath == null || filePath.isBlank()) {
            throw new InvalidFilePathError(filePath);
        }

        String fileName = new File(filePath).getName();

        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex > 0) {
            fileName = fileName.substring(0, lastDotIndex);
        }

        return fileName;
    }

    private static String readFileContent(String filePath) {
        Path path = Path.of(filePath);

        if (!Files.exists(path)) {
            throw new FileNotFoundError(filePath);
        }

        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw new FileReadError(filePath);
        }
    }

    private static void runSafely(Runnable action) {
        try {
            action.run();
        } catch (RuntimeException e) {
            String message = (e.getMessage() != null && !e.getMessage().isBlank())
                    ? e.getMessage()
                    : e.toString();

            errorPrint(message);

            if (Engine.tableeDebugMode) {
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                errorPrint(sw.toString());
            }
        }
    }

    public static void setErrorHandler(Consumer<String> handler) {
        errorHandler = (handler != null) ? handler : System.err::print;
    }

    public static void setOutputHandler(Consumer<String> handler) {
        outputHandler = (handler != null) ? handler : System.out::print;
    }

    public static void print(String message) {
        outputHandler.accept(message);
    }

    public static void errorPrint(String message) {
        errorHandler.accept(message);
    }

}