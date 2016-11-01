package back_end.model.states;

import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.states.background.BackgroundInformation;
import back_end.model.states.background.IModifiableBackground;

public interface IModifiableEnvironmentState extends IViewableVariableState, IModifiableBackground, IModifiableMethodState {
	
	public boolean containsVariable(String aVariableName);
	
	public void assignVariable(String aName, double aValue);
	
	public void getVariablesInMethod(String aMethodName, Double...aValues) throws InvalidNodeUsageException;
	
	public BackgroundInformation getBackgroundInformation();

}
