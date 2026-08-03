package tablee.runtime;

import tablee.tables.types.TableTabular;

import java.io.File;

public final class Engine {

    public static boolean tableeDebugMode = false;

    private static final TableTabular globalTable = new TableTabular("Global");

    private Engine() {}

    public static void addFile(String filePath) {
        TableTabular newTable = new TableTabular(convertFilePathToTableName(filePath));
        globalTable.addSubtable(newTable);
        // 1. parse file code...
        // 2. apply parsed code to fileTable (for: fileTable.addSubtable(...))
    }

    public static void addCode(String tableName, String code) {
        TableTabular newTable = new TableTabular(tableName);
        globalTable.addSubtable(newTable);
        // 1. parse file code...
        // 2. apply parsed code to fileTable (for: fileTable.addSubtable(...))
    }

    private static String convertFilePathToTableName(String filePath) {
        if (filePath == null || filePath.isBlank()) {
            return null; //!E
        }

        String fileName = new File(filePath).getName();

        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex > 0) {
            fileName = fileName.substring(0, lastDotIndex);
        }

        return fileName;
    }

}