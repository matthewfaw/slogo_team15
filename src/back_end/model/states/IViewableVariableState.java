package back_end.model.states;

import java.util.Collection;

import integration.observe.IObservable;


public interface IViewableVariableState extends IObservable {

    public Collection<String> getVariableKeySet ();

    public double getValue (String aVariable);

}
