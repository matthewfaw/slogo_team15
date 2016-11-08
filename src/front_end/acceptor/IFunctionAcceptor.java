package front_end.acceptor;

import back_end.model.states.IViewableMethodState;

/**
 * Defines the FunctionAcceptor Property. 
 * 
 * Implemented by any module that needs to have 
 * updating Functions pushed to it. 
 *  
 * @see front_end.acceptor 
 * @author George Bernard
 */
public interface IFunctionAcceptor {

	/**
	 * Pushes the Observable Function objects to this object.
	 * 
	 * @see back_end.model.states.IViewableMethodState
	 * @param aMethod
	 */	
	public void giveFunction(IViewableMethodState aMethod);
		
}
