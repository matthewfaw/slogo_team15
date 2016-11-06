package back_end.model.states;

import java.util.Collection;

import back_end.model.node.IReadableInput;

/**
 * Interface for accessing and changing the methods 
 *
 */

public interface IModifiableMethodState extends IViewableMethodState {
	
	/**
	 * This assigns a method name to the input arguments and the execution / body of the methods
	 * 
	 * @param aMethodName
	 * @param aVariableInputs
	 * @param aNode
	 */
	public void assignMethod(String aMethodName, IReadableInput[] aVariableInputs, IReadableInput aNode);
	
	/**
	 * This method gets all the variables associated with the method 
	 * 
	 * @param aMethodName
	 * @return
	 */
	public Collection<IReadableInput> getMethodVariables(String aMethodName);
	
	/**
	 * This method returns the body of the function to be executed 
	 * 
	 * @param aMethodName
	 * @return
	 */
	public IReadableInput getMethodExecutionNode(String aMethodName);
	
	/**
	 * This method gets rid of all current methods
	 */
	public void clearMethods();

}
