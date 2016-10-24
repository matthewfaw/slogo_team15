package back_end.model.states;

import java.util.HashMap;
import java.util.Map;

import back_end.model.node.IReadableInput;
import javafx.util.Pair;

public class MethodState {
	
	private Map<String, Pair<IReadableInput[], IReadableInput>> myMethodMap;
	
	public MethodState() {
		myMethodMap = new HashMap<String, Pair<IReadableInput[], IReadableInput>>();
	}
	
	public void assignMethod(String aMethodName, IReadableInput aNode, IReadableInput...aVariableInputs) {
		Pair<IReadableInput[], IReadableInput> tuple = new Pair<IReadableInput[], IReadableInput>(aVariableInputs, aNode);
		myMethodMap.put(aMethodName, tuple);
	}
	
	public IReadableInput[] getVariablesInMethod(String aMethodName) {
		return myMethodMap.get(aMethodName).getKey();
	}
	
	public int getNumberOfInputs(String aMethodName) {
		return myMethodMap.get(aMethodName).getKey().length;
	}
	
	public IReadableInput getFunction(String aMethodName) {
		return myMethodMap.get(aMethodName).getValue();
	}

}
