package tablee.packages.stdlib;

import tablee.packages.PackageBase;
import tablee.runtime.Engine;
import tablee.tables.VariableTypes;
import tablee.tables.types.TableFunctional;
import tablee.tables.types.TableVariable;

import java.util.List;

public class IOPackage extends PackageBase {

    public IOPackage() {
        super("io");
        register();
    }

    @Override
    protected void setupPackage() {
        TableVariable textArg = new TableVariable("text", VariableTypes.String, false);

        getPackageRootTable().addSubtable(
                new TableFunctional(
                        "println",
                        List.of(textArg),
                        args -> {
                            Object text = args.getFirst();
                            Engine.print(text + "\n");
                        }
                )
        );

        getPackageRootTable().addSubtable(
                new TableFunctional(
                        "print",
                        List.of(textArg),
                        args -> {
                            Object text = args.getFirst();
                            Engine.print(text.toString());
                        }
                )
        );
    }

}
