package back_end.model.states;

import java.util.HashMap;
import java.util.Map;

import back_end.model.node.Node;
import javafx.util.Pair;

public class MethodState {
	
	private Map<String, Pair<Integer, Node>> myMethodMap;
	
	public MethodState() {
		myMethodMap = new HashMap<String, Pair<Integer, Node>>();
	}
	
	public void assignMethod(String aMethodName, int aNumberOfInputs, Node aNode) {
		Pair<Integer, Node> tuple = new Pair<Integer, Node>(aNumberOfInputs, aNode);
		myMethodMap.put(aMethodName, tuple);
	}
	
	public int getNumberOfInputs(String aMethodName) {
		return myMethodMap.get(aMethodName).getKey();
	}
	
	public Node getFunction(String aMethodName) {
		return myMethodMap.get(aMethodName).getValue();
	}

}
