package back_end.model.command;

import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;

public class SetPaletteCommand implements ICommand, ICommandTurtle {
	
	private IModifiableEnvironmentState myEnvironment;
	
	public SetPaletteCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
		myEnvironment = aEnvironment;
	}

	@Override
	public double eval(IReadableInput... aList) throws InvalidNodeUsageException {
		myEnvironment.setPaletteColors((int) aList[0].getValue(), (int) aList[1].getValue(), (int) aList[2].getValue(), (int) aList[3].getValue()); 
		return aList[0].getValue();
	}

}