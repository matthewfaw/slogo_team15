package back_end.model.states;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import integration.IObservable;
import integration.IObserver;


public class Scope implements IObservable{
	
	private static final String DEFAULT = "DEFAULT";
	
	private Map<String, VariableState> myScopeMap;
	private VariableState currentVariableState;
	private String currentScope;
	private List<IObserver> myObservers;
	
	public Scope() {
		myScopeMap = new HashMap<String, VariableState>();
		currentVariableState = new VariableState();
		myScopeMap.put(DEFAULT, currentVariableState);
		currentScope = DEFAULT;
		myObservers = new ArrayList<IObserver>();
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
		notifyObservers();
		myScopeMap.get(currentScope).assignVariable(aName, aValue);
		
	}
	
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
