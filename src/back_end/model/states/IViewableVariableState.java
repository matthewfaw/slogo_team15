package back_end.model.states;

import java.util.Collection;


public interface IViewableVariableState {

    public Collection<String> getVariableKeySet ();

    public double getValue (String aVariable);

}
