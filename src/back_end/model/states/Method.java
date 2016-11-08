package back_end.model.states;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import back_end.model.node.IReadableInput;
import integration.observe.AbstractObservable;

public class Method extends AbstractObservable {
	
	private IReadableInput myExecutionNode;
	private ArrayList<IReadableInput> myVariables;
	
	public Method() {
	}
	
	/**
	 * Adds a node to be the "body" of the method - aka the execution
	 * Creates an arraylist with all the variables that are input arguments 
	 * @param aMethodName
	 * @param aNode
	 * @param aVariableList
	 */
	public void assignMethod(String aMethodName, IReadableInput aNode, IReadableInput...aVariableList) {
		myExecutionNode = aNode; 
		ArrayList<IReadableInput> variableArrayList = new ArrayList<IReadableInput>(Arrays.asList(aVariableList)); 
		myVariables = variableArrayList;
	}

	/**
	 * Returns the variables that are the input arguments
	 * 
	 * @return
	 */
	public List<IReadableInput> getVariables() {
		return myVariables;
	}
	
	/**
	 * Returns the execution node (body of the method)
	 * @return
	 */
	public IReadableInput getExecutionNode() {
		return myExecutionNode;
	}
	

}