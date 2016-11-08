package back_end.model.states;

import java.util.Collection;

import integration.observe.IObservable;


/**
 * This interface is used by the front-end to get the current variables and their values so that the information can be displayed
 *
 *
 */

public interface IViewableVariableState extends IObservable {

	/**
	 * Get a list of all variable names
	 * @return
	 */
    public Collection<String> getVariableKeySet ();

    /**
     * Get the value associated to a name
     * @param aVariable
     * @return
     */
    public double getValue (String aVariable);

}
