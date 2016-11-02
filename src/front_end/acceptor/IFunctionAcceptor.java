package front_end.acceptor;

import back_end.model.states.IViewableMethodState;

/**
 * Defines the FunctionAcceptor Property. 
 * 
 * Implemented by any module that needs to have 
 * Functions pushed to it. 
 *  
 * @author George Bernard
 */
public interface IFunctionAcceptor {

	public void giveFunction(IViewableMethodState aMethod);
		
}
