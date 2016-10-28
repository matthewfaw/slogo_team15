package back_end.model.text_parser;

import java.lang.reflect.InvocationTargetException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import back_end.model.command.ICommand;
import back_end.model.exception.UnexpectedCommandException;
import back_end.model.robot.Robot;
import back_end.model.states.Scope;


public class CommandFactory {
	
	private static final String PACKAGE_COMMAND = "back_end.model.command.";
	private static final String PACKAGE_RESOURCE = "resources.commandtypes.";
	private static final String TYPE = "CommandTypes";
	
	private ResourceBundle myCommandTypeResources;
	private Robot myRobot;
	private Scope myScope;
	
	public CommandFactory(Scope aScope, Robot aRobot) {
		myCommandTypeResources = PropertyResourceBundle.getBundle(PACKAGE_RESOURCE + TYPE);
		myRobot = aRobot;
		myScope = aScope;
	}
	
	public ICommand makeCommand(String aWord, String command) throws UnexpectedCommandException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		if (aWord.equals("Custom")) {
			return (ICommand) Class.forName(PACKAGE_COMMAND + aWord + "Command").getConstructor(Robot.class, Scope.class, String.class).newInstance(myRobot, myScope, command);
		}
		return (ICommand) Class.forName(PACKAGE_COMMAND + command + "Command").getConstructor(Robot.class, Scope.class, String.class).newInstance(myRobot, myScope, command);
		throw new UnexpectedCommandException("Command " + command + " you entered was not found");
	}

}
