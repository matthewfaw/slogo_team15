package back_end.model.exception;

public class InvalidInputNumberException extends Exception implements IExceptionDebugger {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private int myLineNumber;

    public InvalidInputNumberException () {
    }

    public InvalidInputNumberException (String aMessage) {
        super(aMessage);
    }
    
    public InvalidInputNumberException (String aMessage, int aLineNumber) {
        super(aMessage);
        myLineNumber = aLineNumber;
    }
    
    public int getErrorLineNumber() {
    	return myLineNumber;
    }
    
    public String getErrorMessage() {
		return super.getMessage();
	}

}
