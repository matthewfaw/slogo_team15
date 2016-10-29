package back_end.model.states;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import back_end.model.node.IReadableInput;
import integration.observe.IObservable;
import integration.observe.IRobotObserver;

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
	private List<IRobotObserver> myObservers;
	private Map<String, MethodState> myMethodMap;
	
	private Environment() {
		myObservers = new ArrayList<IRobotObserver>();
		myMethodMap = new HashMap<String, MethodState>();
	}
	public static Environment getInstance()
	{
		return INSTANCE;
	}
	
	void addNestedScope()
	{
		myCurrentScope.addNestedScope();
	}
	void removeNestedScope()
	{
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
		
	}
	
	
	/** Observable **/
	
	@Override
	public void registerObserver(IRobotObserver o) {
		myObservers.add(o);
	}


	@Override
	public void removeObserver(IRobotObserver o) {
		int i = myObservers.indexOf(o);
		if (i > 0) {
			myObservers.remove(i);
		}
	}

	@Override
	public void notifyObservers() {
		for (IRobotObserver observer : myObservers) {
			observer.update();
		}
		
	}
	@Override
	public Collection<String> getVariableKeySet() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public double getValue(String aVariable) {
		// TODO Auto-generated method stub
		return 0;
	}

}
