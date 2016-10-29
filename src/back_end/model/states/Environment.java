package back_end.model.states;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import back_end.model.exception.SameMethodNameException;
import back_end.model.node.IReadableInput;
import integration.observe.IObservable;
import integration.observe.IObserver;

/**
 * A singleton class that keeps track of current variables and methods associated with evaluation
 * 
 * From http://www.oodesign.com/singleton-pattern.html:
 * Usually singletons are used for centralized management of internal or external resources and they provide a global point of access to themselves
 * 
 * Making this class a singleton guarantees that everyone has the same instance of the current scope, so everything is consistent
 * 
 * @author hannahfuchshuber && matthewfaw
 *
 */
public class Environment implements IObservable, IModifiableVariableState, IViewableVariableState {

	public static final Environment INSTANCE = new Environment();
	
	private FunctionScope myCurrentScope;
	private List<IObserver> myObservers;
	private Map<String, MethodState> myMethodMap;
	
	private Environment() {
		myObservers = new ArrayList<IObserver>();
		myMethodMap = new HashMap<String, MethodState>();
	}
	
	public static Environment getInstance() {
		return INSTANCE;
	}
	
	void addNestedScope() {
		myCurrentScope.addNestedScope();
	}
	
	
	void removeNestedScope() {
		myCurrentScope.removeNestedScope();
	}
	
	public void setCurrentScope(FunctionScope aCurrentScope) {
		myCurrentScope = aCurrentScope;
	}
	
	public boolean containsVariable(String name) {
		return myCurrentScope.containsVariable(name);
	}
	
	public double getVariableValue(String aVariable) {
		return myCurrentScope.getVariableValue(aVariable);
	}
	
	public Collection<String> getVariablesInScope() {
		return myCurrentScope.getVariablesInScope();
	}

	public void assignVariable(String aName, double aValue) {
		myCurrentScope.assignVariable(aName, aValue);
		notifyObservers();
	}
	
	public void assignMethod(String aMethodName, IReadableInput aNode, IReadableInput...aVariableInputs) {
		MethodState methodState = new MethodState();
		methodState.assignMethod(aMethodName, aNode, aVariableInputs);
		myMethodMap.put(aMethodName, methodState);
	}
	
	public void getVariablesInMethod(String aMethodName, Double...aValues) {
		MethodState currentMethodState = myMethodMap.get(aMethodName);
		List<IReadableInput> arrayOfVariables = currentMethodState.getVariables(aMethodName);
		for (int i = 0; i < arrayOfVariables.size(); i++) {
			myCurrentScope.assignVariable(arrayOfVariables.get(i).getName(), aValues[i]);
		}
		
	}
	
	@Override
	public Collection<String> getVariableKeySet() {
		return myCurrentScope.getVariableKeySet();
	}
	
	
	@Override
	public double getValue(String aVariable) {
		return myCurrentScope.getVariableValue(aVariable);
	}
	
	
	/** Observable **/
	
	@Override
	public void registerObserver(IObserver o) {
		myObservers.add(o);
	}


	@Override
	public void removeObserver(IObserver o) {
		int i = myObservers.indexOf(o);
		if (i > 0) {
			myObservers.remove(i);
		}
	}

	@Override
	public void notifyObservers() {
		for (IObserver observer : myObservers) {
			observer.update();
		}
		
	}

}
