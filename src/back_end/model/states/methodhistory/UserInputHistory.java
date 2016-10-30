package back_end.model.states.methodhistory;

import java.util.HashMap;
import java.util.Map;

import back_end.model.node.INode;

public class UserInputHistory {
	
	private Map<String, INode> myUserHistory; 
	
	public UserInputHistory() { 
		myUserHistory = new HashMap<String, INode>();
	}
	
	public void storeMethod(String aUserInputString, INode aNode) {
		myUserHistory.put(aUserInputString, aNode);
	}
	
	public void getMethodKeySet() {
		
	}

}
