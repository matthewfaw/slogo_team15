package back_end.model.states;

import java.util.Collection;

import integration.observe.IObservable;

/**
 * This interface is used by the front-end to update their display of the current methods
 * 
 *
 */

public interface IViewableMethodState extends IObservable {
	
	/**
	 * Returns the current method names 
	 * @return
	 */
	public Collection<String> getAllMethodNames();

}
