package back_end.model.command;

import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;

public class TellCommand implements ICommand {
	
	private IModifiableEnvironmentState myEnvironment;
	
	public TellCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
		myEnvironment = aEnvironment;
	}

	@Override
	public double eval(IReadableInput... aList) throws InvalidNodeUsageException {
		myEnvironment.clearActiveTurtles();
		for (int i = 0; i < aList.length; i++) {
			int currentTurtleID = (int) aList[i].getValue();
			for (int j = 1; j <= currentTurtleID; j++) {
				if (!myEnvironment.containsTurtle(j)) {
					myEnvironment.addTurtle(j);
				}
			}
			myEnvironment.addActiveTurtle(currentTurtleID);
			myEnvironment.setTurtleAsCurrentlyActive(currentTurtleID);
		}
		return aList[aList.length - 1].getValue();
	}

}
