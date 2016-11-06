package back_end.model.text_parser;


import java.text.MessageFormat;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import back_end.model.command.ICommand;
import back_end.model.exception.UnexpectedCommandException;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;


/**
 * Class that creates a stack of Nodes based off what the text input is.
 * 
 * @author Hannah Fuchshuber
 *
 */

public class CommandFactory {
	
	private static final String PACKAGE_COMMAND = "back_end.model.command.";
	private static final String ERROR = "resources.errormessages.ErrorMessages";
	
	private IRobot myRobot;
	private IModifiableEnvironmentState myEnvironment;
	private ResourceBundle myErrorMessageResources;
	
	/**
	 * Constructor for the CommandClass that gets passed the items needed to populate the constructor of the command classes. Also makes
	 * a resource bundle
	 * 
	 * @param aEnvironment
	 * @param aRobot
	 */
	public CommandFactory(IModifiableEnvironmentState aEnvironment, IRobot aRobot) {
		myRobot = aRobot;
		myEnvironment = aEnvironment;
		myErrorMessageResources = PropertyResourceBundle.getBundle(ERROR);
	}
	
	/**
	 * This class is a factory method which creates a Command using reflection.
	 * 
	 * @param aUserInputWord
	 * @param aCommandType
	 * @param aLineNumber
	 * @return
	 * @throws UnexpectedCommandException
	 */
	public ICommand makeCommand(String aUserInputWord, String aCommandType, int aLineNumber) throws UnexpectedCommandException {
		try	{ 
			return (ICommand) Class.forName(PACKAGE_COMMAND + aCommandType + "Command").getConstructor(IRobot.class, IModifiableEnvironmentState.class, String.class).
				newInstance(myRobot, myEnvironment, aUserInputWord);
		} catch (Exception e) {
			throw new UnexpectedCommandException(MessageFormat.format(myErrorMessageResources.getString("UnexpectedCommand"), aUserInputWord, aLineNumber, aCommandType), aLineNumber);
		}
	}

}
