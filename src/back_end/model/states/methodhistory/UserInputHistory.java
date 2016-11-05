package back_end.model.states.methodhistory;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import integration.observe.AbstractObservable;

public class UserInputHistory extends AbstractObservable implements IViewableUserInputHistory {
	
	private Set<String> myUserHistory; 
	
	public UserInputHistory() { 
		myUserHistory = new HashSet<String>();
	}
	
	public void storeMethod(String aUserInputString) {
		myUserHistory.add(aUserInputString);
		notifyObservers();
	}
	
	public Collection<String> getHistoryOfUserInputStrings() {
		return myUserHistory;
	}


}
