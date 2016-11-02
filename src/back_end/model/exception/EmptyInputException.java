package back_end.model.exception;

public class EmptyInputException extends Exception implements IExceptionDebugger {

    private static final long serialVersionUID = 1L;
    
    public EmptyInputException () {
    }

    public EmptyInputException (String aMessage) {
        super(aMessage);
    }

	@Override
	public int getErrorLineNumber() {
		return 0;
	}
	
	public String getErrorMessage() {
		return super.getMessage();
	}


}
