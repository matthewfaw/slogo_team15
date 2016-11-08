package back_end.model.states;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * A class that keeps track on the current scope for a function - it's a implementation is done through a stack of maps in 
 * order to allow for nested scoping
 * 
 *
 */

public class FunctionScope {
	
	private Stack<VariableState> myNestedVariableStates;

	/**
	 * This method sets up the Stack and the first map to account for the first layer of scope
	 */
	public FunctionScope() {
		myNestedVariableStates = new Stack<VariableState>();
		myNestedVariableStates.push(new VariableState());
	}
	
	
	/**
	 * Checks if the variable exists in the function
	 * @param name
	 * @return
	 */
	public boolean containsVariable(String name) {
		for (VariableState variableState : myNestedVariableStates) {
			if (variableState.containsVariable(name)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Gets the value for the variable 
	 * 
	 * @param aVariable
	 * @return
	 */
	public double getVariableValue(String aVariable) {
		for (VariableState variableState : myNestedVariableStates) {
			if (variableState.containsVariable(aVariable)) {
				return variableState.getValue(aVariable);
			}
		}
		//XXX: Change to refer to a default value from a resource file, perhaps
		myNestedVariableStates.peek().assignVariable(aVariable, 0); 
		return 0;
	}

	/**
	 * Assigns a variable to the current nested scope - aka the most recent map in the stack.
	 * 
	 * @param aName
	 * @param aValue
	 */
	public void assignVariable(String aName, double aValue) {
		for (VariableState variableState : myNestedVariableStates) {
			if (variableState.containsVariable(aName)) {
				variableState.assignVariable(aName, aValue);
				return;
			}
		}
		myNestedVariableStates.peek().assignVariable(aName, aValue);
	}
	
	/**
	 * Changes the current nested scope, but adding another map to the stack
	 */
	void addNestedScope() {
		myNestedVariableStates.push(new VariableState());
	}
	
	/**
	 * Changes the current nested scope to a previous scope by removing a map from the stack
	 */
	void removeNestedScope() {
		myNestedVariableStates.pop();
	}

	/**
	 * Gets the variable key set for the entire function
	 * @return
	 */
	public Collection<String> getVariableKeySet() {
		Set<String> set = new HashSet<String>();
		for (VariableState variableState : myNestedVariableStates) {
			set.addAll(variableState.getVariableKeySet());
		}
		return set;
	}


}
