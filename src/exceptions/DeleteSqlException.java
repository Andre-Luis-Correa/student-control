package exceptions;

public class DeleteSqlException extends Exception {

    public DeleteSqlException(String message) {
        super(message);
    }

    public DeleteSqlException(String message, Throwable cause) {
        super(message, cause);
    }
}
