package model.states;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class VariableState {
	
	private Map<String, Double> myVariableMap;
	
	public VariableState() {
		myVariableMap = new HashMap<String, Double>();
	}
	
	public void assignVariable(String aVariable, double aValue) {
		myVariableMap.put(aVariable, aValue);
	}
	
	public double getValue(String aVariable) {
		if (!myVariableMap.containsKey(aVariable)) myVariableMap.put(aVariable, (double) 0);
		return myVariableMap.get(aVariable);
	}

	public Collection<String> keySet() {
		return myVariableMap.keySet();
	}
	
}
