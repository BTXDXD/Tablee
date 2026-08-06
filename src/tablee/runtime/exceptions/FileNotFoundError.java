package tablee.runtime.exceptions;

public class FileNotFoundError extends RuntimeException {

    public FileNotFoundError(String filePath) {
        super("The file at path " + filePath + " was not found");
    }

}
