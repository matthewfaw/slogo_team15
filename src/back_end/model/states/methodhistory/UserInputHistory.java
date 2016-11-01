package back_end.model.states.methodhistory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import integration.observe.Observable;

public class UserInputHistory extends Observable implements IViewableUserInputHistory {
	
	private List<String> myUserHistory; 
	
	public UserInputHistory() { 
		myUserHistory = new ArrayList<String>();
	}
	
	public void storeMethod(String aUserInputString) {
		myUserHistory.add(aUserInputString);
	}
	
	public Collection<String> getHistoryOfUserInputStrings() {
		return myUserHistory;
	}


}
