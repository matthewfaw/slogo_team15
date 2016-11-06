package back_end.model.exception;


/**
 * This error is thrown in there is a command name that is not valid
 * 
 * @author hannahfuchshuber
 *
 */

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
	
	public String getErrorMessage() {
		return super.getMessage();
	}
	

}
