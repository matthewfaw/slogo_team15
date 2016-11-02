package back_end.model.exception;

public class SameMethodNameException extends Exception implements IExceptionDebugger {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int myLineNumber;

    public SameMethodNameException () {
    }

    public SameMethodNameException (String aMessage) {
        super(aMessage);
    }

	public SameMethodNameException (String aMessage, int aLineNumber) {
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
