package back_end.model.exception;

/** 
 * Interface that is given to the front-end, it allows for debugging because it returns the error message, but also it returns
 * the line that the error occured on
 * @author hannahfuchshuber
 *
 */

public interface IExceptionDebugger {
	
	public int getErrorLineNumber();
	
	public String getErrorMessage();

}
