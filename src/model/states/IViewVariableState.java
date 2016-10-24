package model.states;

import java.util.Collection;

public interface IViewVariableState {
	
	public Collection<String> getVariableKeySet();
	
	public double getValue(String aVariable);

}
