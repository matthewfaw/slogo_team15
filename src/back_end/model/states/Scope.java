package back_end.model.states;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import back_end.model.node.IReadableInput;
import integration.observe.IObservable;
import integration.observe.IObserver;


public class Scope implements IObservable {
	
	private static final String DEFAULT = "DEFAULT";
	
	private Map<String, VariableState> myScopeMap;
	private String currentScope;
	private List<IObserver> myObservers;
	private MethodState myMethod;
	
	public Scope() {
		myScopeMap = new HashMap<String, VariableState>();
		myMethod = new MethodState();
		myScopeMap.put(DEFAULT,  new VariableState());
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
	
	public boolean containsVariable(String name) {
		return myScopeMap.get(currentScope).containsVariable(name);
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
	
	public int getNumberOfInputs() {
		return myMethod.getNumberOfInputs(currentScope);
	}
	
	public IReadableInput[] getVariablesInMethod() {
		return myMethod.getVariablesInMethod(currentScope);
	}
	
	public IReadableInput getMethodToEvaluate() {
		return myMethod.getFunction(currentScope);
	}
	
	public boolean containsMethod(String aMethod) {
		return myScopeMap.containsKey(aMethod);
	}
	
	public void assignMethod(String aMethod, IReadableInput aNode, IReadableInput...aVariableInputs) {
		myMethod.assignMethod(aMethod, aNode, aVariableInputs);
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
