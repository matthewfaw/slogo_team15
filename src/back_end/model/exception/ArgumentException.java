package back_end.model.exception;

public class ArgumentException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArgumentException() {
	}
	
	public ArgumentException(String message) {
		super(message);
	}
	
	public ArgumentException(Throwable cause) {
		super(cause);
	}
	
	public ArgumentException(String message, Throwable cause) {
		super(message, cause);
	}

}
