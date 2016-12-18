package integration.router;

import back_end.model.exception.IExceptionDebugger;

public interface IErrorRouter {
	/**
	 * distributes Error to modules decided upon inside Router
	 * 
	 * @see IExceptionDebugger
	 * @param aExceptionDebugger
	 */
	public void distributeError( IExceptionDebugger aExceptionDebugger );
}
