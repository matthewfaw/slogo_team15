package back_end.model.states;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;


public class FunctionScope {
	
	private Stack<VariableState> myNestedVariableStates;

	
	public FunctionScope() {
		myNestedVariableStates = new Stack<VariableState>();
		myNestedVariableStates.push(new VariableState());
	}
	
	public boolean containsVariable(String name) {
		for (VariableState variableState : myNestedVariableStates) {
			if (variableState.containsVariable(name)) {
				return true;
			}
		}
		return false;
	}
	
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

	public void assignVariable(String aName, double aValue) {
		for (VariableState variableState : myNestedVariableStates) {
			if (variableState.containsVariable(aName)) {
				variableState.assignVariable(aName, aValue);
				return;
			}
		}
		myNestedVariableStates.peek().assignVariable(aName, aValue);
	}
	
	void addNestedScope() {
		myNestedVariableStates.push(new VariableState());
	}
	
	void removeNestedScope() {
		myNestedVariableStates.pop();
	}

	public Collection<String> getVariableKeySet() {
		Set<String> set = new HashSet<String>();
		for (VariableState variableState : myNestedVariableStates) {
			set.addAll(variableState.getVariableKeySet());
		}
		return set;
	}


}
