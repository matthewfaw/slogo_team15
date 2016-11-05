package front_end.acceptor;

import back_end.model.states.IViewableVariableState;

/**
 * Defines the VariableAcceptor Property. 
 * 
 * Implemented by any module that needs to have 
 * updating variable state pushed to it. 
 * 
 * @author George Bernard
 */
public interface IVariableAcceptor {
	
	/**
	 * Pushes specified Variable state to object
	 */
	public void giveVariables( IViewableVariableState aViewVariableState);
}
