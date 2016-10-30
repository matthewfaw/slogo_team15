package back_end.model.states.methodhistory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import back_end.model.syntax_tree.AbstractSyntaxTree;
import integration.observe.Observable;

public class UserInputHistory extends Observable {
	
	private Map<String, AbstractSyntaxTree> myUserHistory; 
	
	public UserInputHistory() { 
		myUserHistory = new HashMap<String, AbstractSyntaxTree>();
	}
	
	public void storeMethod(String aUserInputString, AbstractSyntaxTree aTree) {
		myUserHistory.put(aUserInputString, aTree);
		notifyObservers();
	}
	
	public Collection<String> getHistoryOfUserInputStrings() {
		return myUserHistory.keySet();
	}
	
	public AbstractSyntaxTree getSyntaxTree(String aUserInput) {
		return myUserHistory.get(aUserInput);
	}


}
