package model.textParser;

import java.lang.reflect.InvocationTargetException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import model.command.ICommand;
import model.robot.Robot;
import model.states.Scope;

public class CommandFactory {
	
	private static final String PACKAGE_COMMAND = "model.command.";
	private static final String PACKAGE_RESOURCE = "resource.languages.";
	private static final String TYPE = "CommandTypes";
	
	private ResourceBundle myCommandTypeResources;
	private Robot myRobot;
	private Scope myScope;
	
	public CommandFactory(Scope aScope, Robot aRobot) {
		myCommandTypeResources = PropertyResourceBundle.getBundle(PACKAGE_RESOURCE + TYPE);
		myRobot = aRobot;
		myScope = aScope;
	}
	
	public ICommand makeCommand(String command) {
		ICommand commandClass = null;
		String type = myCommandTypeResources.getString(command);
		if (type == "Function") {
			try {
				commandClass = (ICommand) Class.forName(PACKAGE_COMMAND + command + "Command").getConstructor().newInstance();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException
					| ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (type == "Turtle") {
			try {
				commandClass = (ICommand) Class.forName(PACKAGE_COMMAND + command + "Command").getConstructor(Robot.class).newInstance(myRobot);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException
					| ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (type == "Branch" || type == "Assignment" || type == "Custom") {
			try {
				commandClass = (ICommand) Class.forName(PACKAGE_COMMAND + command + "Command").getConstructor(Scope.class).newInstance(myScope);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException
					| ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return commandClass;
	}

}
