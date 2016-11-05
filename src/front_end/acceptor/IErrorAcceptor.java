package front_end.acceptor;

import back_end.model.exception.IExceptionDebugger;

/**
 * Defines the ErrorAcceptor Property. 
 * 
 * Implemented by any module that needs to have 
 * updating Error Objects pushed to it. 
 *  
 * @see front_end.acceptor   
 * @author George Bernard
 */
public interface IErrorAcceptor {

	/**
	 * Pushes the relevant exception debugger object to this object
	 * 
	 * @see back_end.model.exception.IExceptionDebugger
	 * @param aException
	 */
	public void giveError(IExceptionDebugger aException);
	
}
