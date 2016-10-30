package back_end.model.states.methodhistory;

import java.util.HashMap;
import java.util.Map;

import back_end.model.node.Node;

public class UserInputHistory {
	
	private Map<String, Node> myUserHistory; 
	
	public UserInputHistory() { 
		myUserHistory = new HashMap<String, Node>();
	}
	
	public void storeMethod(String aUserInputString, Node aNode) {
		myUserHistory.put(aUserInputString, aNode);
	}
	
	public void getMethodKeySet() {
		
	}

}
