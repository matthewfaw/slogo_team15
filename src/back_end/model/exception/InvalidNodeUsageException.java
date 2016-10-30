package back_end.model.exception;

public class InvalidNodeUsageException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidNodeUsageException (String message) {
        super(message);
    }
}
