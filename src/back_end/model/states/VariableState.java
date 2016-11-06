package back_end.model.states;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that functions as keeping track of the variables and is like a wrapper class for a map. Helps with keeping the classes
 * that own this class more clean, because they don't need to keep track of a map.
 *
 */

public class VariableState {
	
	private Map<String, Double> myVariableMap;
	
	public VariableState() {
		myVariableMap = new HashMap<String, Double>();
	}
	
	/**
	 * Assigns a variable by putting it in map
	 * @param aVariable
	 * @param aValue
	 */
	public void assignVariable(String aVariable, double aValue) {
		myVariableMap.put(aVariable, aValue);
	}
	
	/**
	 * Gets the value associated to a variable name
	 * @param aVariable
	 * @return
	 */
	public double getValue(String aVariable) {
		if (!myVariableMap.containsKey(aVariable)) myVariableMap.put(aVariable, (double) 0);
		return myVariableMap.get(aVariable);
	}
	
	/**
	 * Checks whether variable exists
	 * @param aVariable
	 * @return
	 */
	public boolean containsVariable(String aVariable) {
		return myVariableMap.containsKey(aVariable);
	}

	/**
	 * Returns the variables
	 * @return
	 */
	public Collection<String> getVariableKeySet() {
		return myVariableMap.keySet();
	}
	
}
