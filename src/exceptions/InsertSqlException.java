package exceptions;

public class InsertSqlException extends Exception {

    public InsertSqlException(String message) {
        super(message);
    }

    public InsertSqlException(String message, Throwable cause) {
        super(message, cause);
    }
}
