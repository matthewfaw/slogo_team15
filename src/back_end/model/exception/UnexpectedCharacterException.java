package back_end.model.exception;

public class UnexpectedCharacterException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public UnexpectedCharacterException () {
    }

    public UnexpectedCharacterException (String message) {
        super(message);
    }

    public UnexpectedCharacterException (Throwable cause) {
        super(cause);
    }

    public UnexpectedCharacterException (String message, Throwable cause) {
        super(message, cause);
    }

}
