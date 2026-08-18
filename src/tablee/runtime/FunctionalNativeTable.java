package tablee.runtime;

import java.util.List;

@FunctionalInterface
public interface FunctionalNativeTable {
    Object execute(List<Object> args);
}