package back_end.model.states;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import integration.observe.IObservable;
import integration.observe.IRobotObserver;

public class ScopeController implements IObservable {
	
	private static final String DEFAULT = "DEFAULT"; 
	
	private String myCurrentFunctionScope;
	private Map<String, FunctionScope> myScopeMap;
	private List<IRobotObserver> myObservers;
	
	public ScopeController() {
		myCurrentFunctionScope = DEFAULT;
		swapScope(myCurrentFunctionScope);
		myScopeMap = new HashMap<String, FunctionScope>();
		myObservers = new ArrayList<IRobotObserver>();
	}

	public void swapScope(String aMethod) {
		if (!myScopeMap.containsKey(aMethod)) {
			FunctionScope scope = new FunctionScope();
			myScopeMap.put(aMethod, scope);
		}
		myCurrentFunctionScope = aMethod;
		notifyObservers();
	}
	
	public void addNestedScope() {
		myScopeMap.get(myCurrentFunctionScope).addNestedScope();
		notifyObservers();
	}
	
	public void removeNestedScope() {
		myScopeMap.get(myCurrentFunctionScope).removeNestedScope();
		notifyObservers();
	}
	
	public boolean containsVariable(String name) {
		return myScopeMap.get(myCurrentFunctionScope).containsVariable(name);
	}
	
	public double getVariableValue(String aVariable) {
		return myScopeMap.get(myCurrentFunctionScope).getVariableValue(aVariable);
	}
	
	public Collection<String> getVariablesInScope() {
		return myScopeMap.get(myCurrentFunctionScope).getVariablesInScope();
	}

	public void assignVariable(String aName, double aValue) {
		myScopeMap.get(myCurrentFunctionScope).assignVariable(aName, aValue);
		notifyObservers();
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
	
}
