package tablee.runtime;

import java.util.List;

@FunctionalInterface
public interface FunctionalNativeTable {
    void execute(List<Object> args);
}