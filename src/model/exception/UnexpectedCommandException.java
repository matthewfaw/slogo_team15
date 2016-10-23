package model.exception;

public class UnexpectedCommandException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UnexpectedCommandException() {	
	}
	
	public UnexpectedCommandException(String message) {
		super(message);
	}
	
	public UnexpectedCommandException(Throwable cause) {
		super(cause);
	}
	
	public UnexpectedCommandException(String message, Throwable cause) {
		super(message, cause);
	}

}
