package front_end.acceptor;

import back_end.model.states.IViewableVariableState;

/**
 * Defines the VariableAcceptor Property. 
 * 
 * Implemented by any module that needs to have 
 * updating variable state pushed to it. 
 * 
 * @see front_end.acceptor
 * @author George Bernard
 */
public interface IVariableAcceptor {
	
	
	/**
	 * Pushes Observable Variable State object to this object
	 * 
	 * @see back_end.model.states.IViewableVariableState
	 * @param aViewVariableState
	 */
	public void giveVariables( IViewableVariableState aViewVariableState);
}
