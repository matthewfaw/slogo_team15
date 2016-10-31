package back_end.model.states;

import java.util.Collection;

import integration.observe.IObservable;

public interface IViewableMethodState extends IObservable {
	
	public Collection<String> getAllMethodNames();

}
