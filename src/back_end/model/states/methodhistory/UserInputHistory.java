package back_end.model.states.methodhistory;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import integration.observe.Observable;

/**
 * Information class that stores all previously inputed user information
 */

public class UserInputHistory extends Observable implements IViewableUserInputHistory {
	
	private Set<String> myUserHistory; 
	
	public UserInputHistory() { 
		myUserHistory = new HashSet<String>();
	}
	
	/**SETTER**/
	
	public void storeMethod(String aUserInputString) {
		myUserHistory.add(aUserInputString);
		notifyObservers();
	}
	
	/**GETTER**/
	
	public Collection<String> getHistoryOfUserInputStrings() {
		return myUserHistory;
	}


}
