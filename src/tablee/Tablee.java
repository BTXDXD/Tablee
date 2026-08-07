package tablee;

import tablee.packages.stdlib.IOPackage;
import tablee.runtime.Config;
import tablee.runtime.Engine;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Tablee {

    private static final String help = """
             Tablee Programming Language v""" + Config.tableeVersion + """
              
               Copyright (C) 2026 btxdxd.
               Licensed under the GNU General Public License v3.0 (GPLv3).
               This is free software: you are free to change and redistribute it.
               GitHub: https://github.com/BTXDXD/Tablee
 
             Usage:
               java -jar tablee.jar <path_to_file.tab> [args]
 
             Args and Commands:
               --help       Show this help message and exit
               --version    Show the current language version and exit
               --tabDebug   Enable extended debug output (Debug)
             """;

    private static final String version = """
             Tablee Programming Language v""" + Config.tableeVersion + """
              
               Copyright (C) 2026 btxdxd.
               Licensed under the GNU General Public License v3.0 (GPLv3).
               This is free software: you are free to change and redistribute it.
               GitHub: https://github.com/BTXDXD/Tablee
             """;

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println(help);
            return;
        }

        String mainFilePath = null;

        List<String> argsList = List.of(args);

        if (argsList.contains("--help")) {System.out.println(help); return;}
        else if (argsList.contains("--version")) {System.out.println(version); return;}

        if (argsList.contains("--tabDebug")) {
            Engine.tableeDebugMode = true;}

        for (String arg : args) {
            if (arg.equals("--help") || arg.equals("--version") || arg.equals("--tabDebug")) continue;
            else if (!arg.startsWith("-")) mainFilePath = arg;
            else {
                System.out.println("Unknown arg: " + arg);
                return;
            }
        }

        if (mainFilePath == null) {
            System.out.println("No executable script specified");
            return;
        }

        if (Files.exists(Path.of(mainFilePath))) {
            registerBasePackages();
            Engine.addFile(mainFilePath);
        } else {
            System.out.println("File not found: " + mainFilePath);
        }
    }

    private static void registerBasePackages() {
        new IOPackage();
    }

}