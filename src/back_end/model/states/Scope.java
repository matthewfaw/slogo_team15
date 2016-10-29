package back_end.model.states;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import back_end.model.node.IReadableInput;
import integration.observe.IObservableRobot;
import integration.observe.IRobotObserver;


public class Scope implements IObservableRobot {
	
	private static final String DEFAULT = "DEFAULT";
	
	private Map<String, VariableState> myScopeMap;
	private Map<String, List<String>> myInputVariableMap;
	private Map<String, IReadableInput> myFunctionMap;
	private String currentScope;
	private List<IRobotObserver> myObservers;
	
	private MethodState myMethod;
	
	public Scope() {
		myScopeMap = new HashMap<String, VariableState>();
		myInputVariableMap = new HashMap<String, List<String>>();
		myFunctionMap = new HashMap<String, IReadableInput>();
		myMethod = new MethodState();
		myScopeMap.put(DEFAULT,  new VariableState());
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
	
//	public int getNumberOfInputs() {
//		return myMethod.getNumberOfInputs(currentScope);
//	}
	
//	public IReadableInput[] getVariablesInMethod(String aMethodName) {
//		return myMethod.getVariablesInMethod(aMethodName);
//	}
	public List<String> getVariablesInMethod(String aMethodName) {
		return myInputVariableMap.get(aMethodName);
	}
	
	public IReadableInput getMethodToEvaluate(String aMethodName) {
		return myFunctionMap.get(aMethodName);
//		return myMethod.getFunction(aMethodName);
	}
	
	public boolean containsMethod(String aMethod) {
		return myScopeMap.containsKey(aMethod);
	}
	
	public void assignMethod(String aMethod, IReadableInput aNode, IReadableInput...aVariableInputs) {
		myFunctionMap.put(aMethod, aNode);
		for (IReadableInput input: aVariableInputs) {
			if (!myInputVariableMap.containsKey(aMethod)) {
				myInputVariableMap.put(aMethod, new ArrayList<String>());
			}
			myInputVariableMap.get(aMethod).add(input.getName());
		}
		myMethod.assignMethod(aMethod, aNode, aVariableInputs);
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
