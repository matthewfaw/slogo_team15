package back_end.model.states;

import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.IReadableInput;
import back_end.model.states.background.IViewableBackground;

public interface IModifiableEnvironmentState extends IViewableVariableState, IViewableBackground {
	
	public boolean containsVariable(String aVariableName);
	
	public double getVariableValue(String aVariable);
	
	public void assignVariable(String aName, double aValue);
	
	public void assignMethod(String aMethodName, IReadableInput aNode, IReadableInput...aVariableInputs);
	
	public void getVariablesInMethod(String aMethodName, Double...aValues) throws InvalidNodeUsageException;
	
	public void setBackgroundColor(int aColor);

}
