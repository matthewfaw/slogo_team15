package back_end.model.text_parser;

import java.lang.reflect.InvocationTargetException;
import back_end.model.command.ICommand;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;


public class CommandFactory {
	
	private static final String PACKAGE_COMMAND = "back_end.model.command.";
	
	private IRobot myRobot;
	private IModifiableEnvironmentState myEnvironment;
	
	public CommandFactory(IModifiableEnvironmentState aEnvironment, IRobot aRobot) {
		myRobot = aRobot;
		myEnvironment = aEnvironment;
	}
	
	public ICommand makeCommand(String aUserInputWord, String aCommandType) throws InstantiationException, IllegalAccessException, 
																			IllegalArgumentException, InvocationTargetException, 
																			NoSuchMethodException, SecurityException, ClassNotFoundException {
		return (ICommand) Class.forName(PACKAGE_COMMAND + aCommandType + "Command").getConstructor(IRobot.class, IModifiableEnvironmentState.class, String.class).
				newInstance(myRobot, myEnvironment, aUserInputWord);
	}

}
