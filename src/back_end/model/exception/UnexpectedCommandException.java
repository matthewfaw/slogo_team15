package back_end.model.exception;

public class UnexpectedCommandException extends Exception implements IExceptionDebugger {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private int myLineNumber;

    public UnexpectedCommandException () {
    }

    public UnexpectedCommandException (String aMessage) {
        super(aMessage);
    }

    public UnexpectedCommandException (String aMessage, int aLineNumber) {
    	super(aMessage);
    	myLineNumber = aLineNumber;
    }
    

	@Override
	public int getErrorLineNumber() {
		return myLineNumber;
	}
	

}
