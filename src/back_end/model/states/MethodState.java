package back_end.model.states;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import back_end.model.node.IReadableInput;

public class MethodState {
	
	private Map<String, IReadableInput> myMethodEvaluation;
	private Map<String, ArrayList<IReadableInput>> myMethodVariables;
	
	public MethodState() {
		myMethodEvaluation = new HashMap<String, IReadableInput>();
		myMethodVariables = new HashMap<String, ArrayList<IReadableInput>>();
	}
	
	public void assignMethod(String aMethodName, IReadableInput aNode, IReadableInput...aVariableList) {
		if (!myMethodEvaluation.containsKey(aMethodName)) {
			myMethodEvaluation.put(aMethodName, aNode);
			ArrayList<IReadableInput> variableArrayList = new ArrayList<IReadableInput>(Arrays.asList(aVariableList)); 
			myMethodVariables.put(aMethodName, variableArrayList);
		}
	}

	public List<IReadableInput> getVariables(String aMethodName) {
		return myMethodVariables.get(aMethodName);
	}
	

}
