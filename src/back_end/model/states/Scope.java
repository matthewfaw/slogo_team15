package back_end.model.states;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import integration.observe.IObservable;
import integration.observe.IRobotObserver;


public class Scope implements IObservable {
	
	private static final String DEFAULT = "DEFAULT";
	
	private Map<String, VariableState> myScopeMap;
	private VariableState currentVariableState;
	private String currentScope;
	private List<IRobotObserver> myObservers;
	
	public Scope() {
		myScopeMap = new HashMap<String, VariableState>();
		currentVariableState = new VariableState();
		myScopeMap.put(DEFAULT, currentVariableState);
		currentScope = DEFAULT;
		myObservers = new ArrayList<IRobotObserver>();
	}
	
	public void swapScope(String aMethod) {
		if (!myScopeMap.containsKey(aMethod)) {
			VariableState variableMap = new VariableState();
			myScopeMap.put(aMethod, variableMap);
		}
		currentScope = aMethod;
		notifyObservers();
	}
	
	public VariableState getVariableMap() {
		return myScopeMap.get(currentScope);
	}
	
	public double getValue(String aVariable) {
		notifyObservers();
		return myScopeMap.get(currentScope).getValue(aVariable);
	}
	
	public Collection<String> getVariablesInScope() {
		return myScopeMap.get(currentScope).getVariableKeySet();
	}

	public void assignVariable(String aName, double aValue) {
		myScopeMap.get(currentScope).assignVariable(aName, aValue);
		notifyObservers();
		
	}
	
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
