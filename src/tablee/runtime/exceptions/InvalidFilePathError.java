package tablee.runtime.exceptions;

public class InvalidFilePathError extends RuntimeException {

    public InvalidFilePathError(String filePath) {
        super("The file path " + filePath + " cannot be null or empty");
    }

}