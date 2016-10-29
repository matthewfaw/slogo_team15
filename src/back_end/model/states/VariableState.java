package back_end.model.states;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class VariableState implements IViewableVariableState {
	
	private Map<String, Double> myVariableMap;
	
	public VariableState() {
		myVariableMap = new HashMap<String, Double>();
	}
	
	public void assignVariable(String aVariable, double aValue) {
		myVariableMap.put(aVariable, aValue);
	}
	
	@Override
	public double getValue(String aVariable) {
		if (!myVariableMap.containsKey(aVariable)) myVariableMap.put(aVariable, (double) 0);
		return myVariableMap.get(aVariable);
	}
	
	public boolean containsVariable(String aVariable) {
		return myVariableMap.containsKey(aVariable);
	}
	
	public Map<String, Double> getVariableMap() {
		return myVariableMap;
	}
	
	public void combineVariableMap(Map<String, Double> aMap) {
		myVariableMap.putAll(aMap);
	}

	@Override
	public Collection<String> getVariableKeySet() {
		return myVariableMap.keySet();
	}
	
}
