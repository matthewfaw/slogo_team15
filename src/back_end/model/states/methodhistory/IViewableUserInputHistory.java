package back_end.model.states.methodhistory;

import java.util.Collection;

import integration.observe.IObservable;

/**
 * 
 * Interface to the front-end so that they can get the Strings assocaited with User Input
 *
 */

public interface IViewableUserInputHistory extends IObservable {
	
	public Collection<String> getHistoryOfUserInputStrings();

}
