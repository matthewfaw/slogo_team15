package back_end.model.states;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import back_end.model.node.IReadableInput;
import integration.observe.IObservable;
import integration.observe.IRobotObserver;


public class FunctionScope implements Iterable {
	
	private Stack<VariableState> myNestedVariableStates;

	
	public FunctionScope() {
		myNestedVariableStates = new Stack<VariableState>();
		myNestedVariableStates.push(new VariableState());
	}
	
	public VariableState getVariableMap() {
		VariableState returnVal = new VariableState(); 
		for (VariableState variableState : myNestedVariableStates) {
			
		}
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
		return 0;
	}
	
	public Collection<String> getVariablesInScope() {
		Set<String> keySet = new HashSet<String>(); 
		for (VariableState variableState : myNestedVariableStates) {
			keySet.addAll(variableState.getVariableKeySet()); 
		}
		return keySet;
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
	
	public void addNestedScope() {
		myNestedVariableStates.push(new VariableState());
	}
	
	public void removeNestedScope() {
		myNestedVariableStates.pop();
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}


}
