package front_end.acceptor;

import back_end.model.states.IViewVariableState;

/**
 * Defines the VariableAcceptor Property. 
 * 
 * Implemented by any module that needs to have 
 * variable state pushed to it. 
 * 
 * @author George Bernard
 */
public interface IVariableAcceptor {
	
	/**
	 * Pushes specified Variable state to object
	 */
	public void giveVariables( IViewVariableState aViewVariableState);
}
