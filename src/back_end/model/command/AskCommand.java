package back_end.model.command;

import back_end.model.exception.InvalidInputNumberException;
import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;

public class AskCommand extends ICommandBranch {
	
	private IModifiableEnvironmentState myEnvironment;
	private boolean myExecuteMethod;
	
	public AskCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
		myEnvironment = aEnvironment;
		myExecuteMethod = true;
	}

	//TODO: Refactor, since same code as Tell Method
	@Override
	public int evalConditionInNode (IReadableInput...aList) throws InvalidInputNumberException, InvalidNodeUsageException {
//		myEnvironment.clearActiveTurtles();
		if (myExecuteMethod) {
			myExecuteMethod = false;
			for (int i = 0; i < aList.length; i++) {
				int currentTurtleID = (int) aList[i].getValue();
				for (int j = 1; j <= currentTurtleID; j++) {
					if (!myEnvironment.containsTurtle(j)) {
						myEnvironment.addTurtle(j);
					}
				}
				myEnvironment.addActiveTurtle(currentTurtleID);
				if (i == 0) {
					myEnvironment.setTurtleAsCurrentlyActive(currentTurtleID);
				}
			}
			return 0;
		}
		return -1;
	}

}
