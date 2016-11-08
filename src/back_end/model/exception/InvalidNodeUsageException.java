package back_end.model.exception;



/***
 * This is the exception thrown if a node cannot be the child of another node
 *
 */

public class InvalidNodeUsageException extends Exception implements IExceptionDebugger {

	private static final long serialVersionUID = 1L;
	
	private int myLineNumber;
	
	public InvalidNodeUsageException() {
	}

	public InvalidNodeUsageException (String aMessage) {
        super(aMessage);
    }
	
	public InvalidNodeUsageException (String aMessage, int aLineNumber) {
		super(aMessage);
		myLineNumber = aLineNumber;
	}

	@Override
	public int getErrorLineNumber() {
		return myLineNumber;
	}
	
	public String getErrorMessage() {
		return super.getMessage();
	}
}
