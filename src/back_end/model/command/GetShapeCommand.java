package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;

public class GetShapeCommand implements ICommand {
	
	private IRobot myRobot;
	
	public GetShapeCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
		myRobot = aRobot;
	}

	@Override
	public double eval(IReadableInput... aList) {
		//return myRobot.getShape();
		//TODO
		return 0;
	}

}