package back_end.model.exception;

/**
 * This error is thrown in Text Parsing if the user inputs a command / word that is not known in this language
 *
 */

public class UnexpectedCharacterException extends Exception implements IExceptionDebugger {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private int myLineNumber;

    public UnexpectedCharacterException () {
    }

    public UnexpectedCharacterException (String aMessage) {
        super(aMessage);
    }

    public UnexpectedCharacterException (String aMessage, int aLineNumber) {
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
