package back_end.model.exception;

public class EmptyInputException extends Exception {

    private static final long serialVersionUID = 1L;

    public EmptyInputException () {
    }

    public EmptyInputException (String message) {
        super(message);
    }

    public EmptyInputException (Throwable cause) {
        super(cause);
    }

    public EmptyInputException (String message, Throwable cause) {
        super(message, cause);
    }

}
