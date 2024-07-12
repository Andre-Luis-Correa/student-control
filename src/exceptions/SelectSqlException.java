package exceptions;

public class SelectSqlException extends Exception {

    public SelectSqlException(String message) {
        super(message);
    }

    public SelectSqlException(String message, Throwable cause) {
        super(message, cause);
    }
}
