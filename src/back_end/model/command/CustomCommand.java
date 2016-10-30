package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.Environment;
import back_end.model.states.IModifiableEnvironmentState;

public class CustomCommand extends ICommandBranch {
	
	private IModifiableEnvironmentState myEnvironment;
	private String myName;
	
	public CustomCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
		myEnvironment = aEnvironment;
		myName = aCommandName;
	}

	@Override
	public int evalCondition(IReadableInput... aList) {
		int counter = 0;
		//for (String variable: myEnvironment.getVariablesInScope()) {
			//myEnvironment.assignVariable(variable, aList[counter].getValue());
			++counter;
		//}
//		IReadableInput[] variableList = myScope.getVariablesInMethod(myName);
//		for (int i = 0; i < variableList.length; i++) {
//			myScope.assignVariable(variableList[i].getName(), aList[i].getValue());
//		}
		return 1;
	}
	
	//public IReadableInput getFunction() {
		//return myEnvironment.getMethodToEvaluate(myName);
	//}
	

}
