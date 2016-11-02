package back_end.model.text_parser;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import back_end.model.command.ICommand;
import back_end.model.exception.UnexpectedCommandException;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;


public class CommandFactory {
	
	private static final String PACKAGE_COMMAND = "back_end.model.command.";
	private static final String ERROR = "resources.errormessages.ErrorMessages";
	
	private IRobot myRobot;
	private IModifiableEnvironmentState myEnvironment;
	private ResourceBundle myErrorMessageResources;
	
	public CommandFactory(IModifiableEnvironmentState aEnvironment, IRobot aRobot) {
		myRobot = aRobot;
		myEnvironment = aEnvironment;
		myErrorMessageResources = PropertyResourceBundle.getBundle(ERROR);
	}
	
	public ICommand makeCommand(String aUserInputWord, String aCommandType, int aLineNumber) throws InstantiationException, IllegalAccessException, 
																			IllegalArgumentException, InvocationTargetException, 
																			NoSuchMethodException, SecurityException, ClassNotFoundException, UnexpectedCommandException {
		try	{ 
			return (ICommand) Class.forName(PACKAGE_COMMAND + aCommandType + "Command").getConstructor(IRobot.class, IModifiableEnvironmentState.class, String.class).
				newInstance(myRobot, myEnvironment, aUserInputWord);
		} catch (InstantiationException | IllegalAccessException | 
				IllegalArgumentException | InvocationTargetException | 
				NoSuchMethodException | SecurityException | ClassNotFoundException | MissingResourceException e) {
			throw new UnexpectedCommandException(MessageFormat.format(myErrorMessageResources.getString("UnexpectedCommand"), aUserInputWord, aLineNumber, aCommandType), aLineNumber);
		}
	}

}
