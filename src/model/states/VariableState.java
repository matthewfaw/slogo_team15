package model.states;

import java.util.HashMap;
import java.util.Map;

public class VariableState {
	
	private Map<String, String> myVariableMap;
	
	public VariableState() {
		myVariableMap = new HashMap<String, String>();
	}
	
	public void assignVariable(String aVariable, String aValue) {
		myVariableMap.put(aVariable, aValue);
	}
	
	public void getValue(String aVariable) {
		myVariableMap.get(aVariable);
	}
	
}
