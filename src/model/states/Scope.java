package model.states;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Scope {
	
	private static final String DEFAULT = "DEFAULT";
	
	private Map<String, VariableState> myScopeMap;
	private VariableState currentVariableState;
	private String currentScope;
	
	public Scope() {
		myScopeMap = new HashMap<String, VariableState>();
		currentVariableState = new VariableState();
		myScopeMap.put(DEFAULT, currentVariableState);
		currentScope = DEFAULT;
	}
	
	public void swapScope(String aMethod) {
		if (!myScopeMap.containsKey(aMethod)) {
			VariableState variableMap = new VariableState();
			myScopeMap.put(aMethod, variableMap);
		}
		currentScope = aMethod;
	}
	
	public VariableState getVariableMap(String aMethod) {
		return myScopeMap.get(aMethod);
	}
	
	public double getValue(String aVariable) {
		return myScopeMap.get(currentScope).getValue(aVariable);
	}
	
	public Collection<String> getVariablesInScope() {
		return myScopeMap.get(currentScope).keySet();
	}

}
