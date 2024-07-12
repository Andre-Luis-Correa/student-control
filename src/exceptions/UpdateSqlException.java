package exceptions;

public class UpdateSqlException extends Exception {

    public UpdateSqlException(String message) {
        super(message);
    }

    public UpdateSqlException(String message, Throwable cause) {
        super(message, cause);
    }
}
