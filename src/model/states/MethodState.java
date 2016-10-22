package model.states;

import java.util.HashMap;
import java.util.Map;
import model.node.INode;

import javafx.util.Pair;

public class MethodState {
	
	private Map<String, Pair<Integer, INode>> myMethodMap;
	
	public MethodState() {
		myMethodMap = new HashMap<String, Pair<Integer, INode>>();
	}
	
	public void assignMethod(String aMethodName, int aNumberOfInputs, INode aNode) {
		Pair<Integer, INode> tuple = new Pair<Integer, INode>(aNumberOfInputs, aNode);
		myMethodMap.put(aMethodName, tuple);
	}
	
	public int getNumberOfInputs(String aMethodName) {
		return myMethodMap.get(aMethodName).getKey();
	}
	
	public INode getFunction(String aMethodName) {
		return myMethodMap.get(aMethodName).getValue();
	}

}
