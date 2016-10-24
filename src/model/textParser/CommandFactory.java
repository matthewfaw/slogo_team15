package model.textParser;

import java.lang.reflect.InvocationTargetException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import model.command.ICommand;
import model.exception.UnexpectedCommandException;
import model.robot.Robot;
import model.states.Scope;

public class CommandFactory {
	
	private static final String PACKAGE_COMMAND = "model.command.";
	private static final String PACKAGE_RESOURCE = "resources.languages.";
	private static final String TYPE = "CommandTypes";
	
	private ResourceBundle myCommandTypeResources;
	private Robot myRobot;
	private Scope myScope;
	
	public CommandFactory(Scope aScope, Robot aRobot) {
		myCommandTypeResources = PropertyResourceBundle.getBundle(PACKAGE_RESOURCE + TYPE);
		myRobot = aRobot;
		myScope = aScope;
	}
	
	public ICommand makeCommand(String command) throws UnexpectedCommandException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		String type = myCommandTypeResources.getString(command);
		if (type.equals("Function") ) {
			return (ICommand) Class.forName(PACKAGE_COMMAND + command + "Command").getConstructor().newInstance();
		}
		if (type.equals("Turtle")) {
			return (ICommand) Class.forName(PACKAGE_COMMAND + command + "Command").getConstructor(Robot.class).newInstance(myRobot);
		}
		if (type.equals("Branch") || type.equals("Assignment") || type.equals("Custom")) {
			return (ICommand) Class.forName(PACKAGE_COMMAND + command + "Command").getConstructor(Scope.class).newInstance(myScope);
		}
		throw new UnexpectedCommandException("Command not found");
	}

}
