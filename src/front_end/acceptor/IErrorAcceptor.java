package front_end.acceptor;

import back_end.model.exception.IExceptionDebugger;

/**
 * Defines the ErrorAcceptor Property. 
 * 
 * Implemented by any module that needs to have 
 * updating Error Objects pushed to it. 
 *  
 * @author George Bernard
 */
public interface IErrorAcceptor {

	public void giveError(IExceptionDebugger aException);
	
}
