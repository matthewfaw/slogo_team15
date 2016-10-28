package back_end.model.states;

import java.util.HashMap;
import java.util.Map;

import back_end.model.exception.SameMethodNameException;
import back_end.model.node.IReadableInput;

public class MethodState {
	
	private Map<String, IReadableInput> myMethodEvaluation;
	
	public MethodState() {
		myMethodEvaluation = new HashMap<String, IReadableInput>();
	}
	
	public void assignMethod(String aMethodName, IReadableInput aNode) throws SameMethodNameException {
		if (myMethodEvaluation.containsKey(aMethodName)) throw new SameMethodNameException(); 
		myMethodEvaluation.put(aMethodName, aNode);
	}
	

}
