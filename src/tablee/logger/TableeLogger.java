package tablee.logger;

import tablee.runtime.Engine;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public final class TableeLogger {

    private static final String RESET = "\u001B[0m";
    private static final String GRAY = "\u001B[90m";
    private static final String CYAN = "\u001B[36m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RED = "\u001B[31m";
    private static final String FATAL_STYLE = "\u001B[41;1;37m";

    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    private TableeLogger() {}

    private static String getTime() {
        return LocalTime.now().format(TIME_FORMAT);
    }

    public static void debug(String message) {
        if (isDebugModeEnabled())
            System.out.println(GRAY + "[" + getTime() + "]#[DEBUG] " + message + RESET);
    }

    public static void info(String message) {
        if (isDebugModeEnabled())
            System.out.println(CYAN + "[" + getTime() + "]#[INFO ] " + message + RESET);
    }

    public static void warn(String message) {
        if (isDebugModeEnabled())
            System.out.println(YELLOW + "[" + getTime() + "]#[WARN ] " + message + RESET);
    }

    public static void error(String message) {
        if (isDebugModeEnabled())
            System.out.println(RED + "[" + getTime() + "]#[ERROR]" + message + RESET);
    }

    public static void fatal(String message) {
        if (isDebugModeEnabled()) {
            System.out.println(FATAL_STYLE + "[" + getTime() + "]#[FATAL]" + message + RESET);
            throw new RuntimeException(message);
        }
    }

    private static boolean isDebugModeEnabled() {
        return Engine.tableeDebugMode;
    }

}