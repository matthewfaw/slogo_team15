package back_end.model.text_parser;

import java.lang.reflect.InvocationTargetException;
import back_end.model.command.ICommand;
import back_end.model.robot.Robot;
import back_end.model.states.IModifiableVariableState;


public class CommandFactory {
	
	private static final String PACKAGE_COMMAND = "back_end.model.command.";
	
	private Robot myRobot;
	private IModifiableVariableState myEnvironment;
	
	public CommandFactory(IModifiableVariableState aEnvironment, Robot aRobot) {
		myRobot = aRobot;
		myEnvironment = aEnvironment;
	}
	
	public ICommand makeCommand(String aUserInputWord, String aCommandType) throws InstantiationException, IllegalAccessException, 
																			IllegalArgumentException, InvocationTargetException, 
																			NoSuchMethodException, SecurityException, ClassNotFoundException {
			return (ICommand) Class.forName(PACKAGE_COMMAND + aCommandType + "Command").getConstructor(Robot.class, IModifiableVariableState.class, String.class).
					newInstance(myRobot, myEnvironment, aUserInputWord);
	}

}
