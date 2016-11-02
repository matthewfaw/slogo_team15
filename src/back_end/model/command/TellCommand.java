package back_end.model.command;

import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;

public class TellCommand implements ICommand, ICommandTurtle {
	
	private IRobot myRobot;
	private IModifiableEnvironmentState myEnvironment;
	
	public TellCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
		myRobot = aRobot;
		myEnvironment = aEnvironment;
	}

	@Override
	public double eval(IReadableInput... aList) throws InvalidNodeUsageException {
		return 0;
		//for (int i = 0; i < aList.length; i++) {
			//if (aList[i] >= //) {
//				addTurtle(aTurtleIDs[i]);
//			}
//		}
//		for (int i = 0; i < aTurtleIDs.length; i++) {
//			myActiveTurtles.add(myTurtles.get(aTurtleIDs[i]));
//		}
//		myTellActiveTurtles.clear();
//		myTellActiveTurtles.addAll(myActiveTurtles);
//		currentTurtle = aTurtleIDs[aTurtleIDs.length - 1];
//		}
	}

}
