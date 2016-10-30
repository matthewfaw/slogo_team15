package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;

public class SetPaletteCommand implements ICommand {
	
	private IRobot myRobot;
	
	public SetPaletteCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
		myRobot = aRobot;
	}

	@Override
	public double eval(IReadableInput... aList) {
		myRobot.getPenInformation().setPenThickness((int) aList[0].getValue());
		return aList[0].getValue();
	}

}