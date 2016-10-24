package back_end.model.exception;

public class SyntaxException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SyntaxException() {	
	}
	
	public SyntaxException(String message) {
		super(message);
	}
	
	public SyntaxException(Throwable cause) {
		super(cause);
	}
	
	public SyntaxException(String message, Throwable cause) {
		super(message, cause);
	}

}
