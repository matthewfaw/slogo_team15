package back_end.model.states;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import back_end.model.node.IReadableInput;
import integration.observe.AbstractObservable;

public class Method extends AbstractObservable {
	
	private IReadableInput myExecutionNode;
	private ArrayList<IReadableInput> myVariables;
	
	public Method() {
	}
	
	public void assignMethod(String aMethodName, IReadableInput aNode, IReadableInput...aVariableList) {
		myExecutionNode = aNode; 
		ArrayList<IReadableInput> variableArrayList = new ArrayList<IReadableInput>(Arrays.asList(aVariableList)); 
		myVariables = variableArrayList;
	}

	public List<IReadableInput> getVariables() {
		return myVariables;
	}
	
	public IReadableInput getExecutionNode() {
		return myExecutionNode;
	}
	

}
