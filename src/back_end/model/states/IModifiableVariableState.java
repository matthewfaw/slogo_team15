package back_end.model.states;

import back_end.model.node.IReadableInput;

public interface IModifiableVariableState extends IViewableVariableState {
	
	public boolean containsVariable(String aVariableName);
	
	public double getVariableValue(String aVariable);
	
	public void assignVariable(String aName, double aValue);
	
	public void assignMethod(String aMethodName, IReadableInput aNode, IReadableInput...aVariableInputs);
	
	public void getVariablesInMethod(String aMethodName, Double...aValues);

}
