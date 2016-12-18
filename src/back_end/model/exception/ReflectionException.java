package back_end.model.exception;

import java.util.ResourceBundle;

public class ReflectionException extends Exception implements IExceptionDebugger {

	private static final long serialVersionUID = 1L;

	public ReflectionException(String aMessage) {
        super(aMessage);
    }

	@Override
	public int getErrorLineNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getErrorMessage() {
		return super.getMessage();
	}

}
