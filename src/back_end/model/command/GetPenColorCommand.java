package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;

public class GetPenColorCommand implements ICommand {
	
	private IRobot myRobot;
	
	public GetPenColorCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
		myRobot = aRobot;
	}

	@Override
	public double eval(IReadableInput... aList) {
		return myRobot.getPenInformation().getColorID();
	}

}