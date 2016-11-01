package back_end.model.states;

import java.util.Collection;

import back_end.model.node.IReadableInput;

public interface IModifiableMethodState extends IViewableMethodState {
	
	public void assignMethod(String aMethodName, IReadableInput[] aVariableInputs, IReadableInput aNode);
	
	public Collection<IReadableInput> getMethodVariables(String aMethodName);
	
	public IReadableInput getMethodExecutionNode(String aMethodName);
	
	public void clearMethods();

}
