package tablee.runtime.exceptions;

public class FileReadError extends RuntimeException {

    public FileReadError(String filePath) {
        super("Unable to read the file at path " + filePath);
    }

}
