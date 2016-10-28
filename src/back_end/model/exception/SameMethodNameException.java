package back_end.model.exception;

public class SameMethodNameException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    public SameMethodNameException () {
    }

    public SameMethodNameException (String message) {
        super(message);
    }

    public SameMethodNameException (Throwable cause) {
        super(cause);
    }

    public SameMethodNameException (String message, Throwable cause) {
        super(message, cause);
    }


}
