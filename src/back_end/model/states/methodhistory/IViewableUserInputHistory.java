package back_end.model.states.methodhistory;

import java.util.Collection;

import integration.observe.IObservable;

public interface IViewableUserInputHistory extends IObservable {
	
	public Collection<String> getHistoryOfUserInputStrings();

}
