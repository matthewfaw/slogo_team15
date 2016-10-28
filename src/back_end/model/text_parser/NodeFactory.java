package back_end.model.text_parser;

import java.lang.reflect.InvocationTargetException;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import back_end.model.command.CustomCommand;
import back_end.model.command.ICommand;
import back_end.model.command.ICommandBranch;
import back_end.model.exception.UnexpectedCharacterException;
import back_end.model.exception.UnexpectedCommandException;
import back_end.model.node.ListStartNode;
import back_end.model.node.ConstantNode;
import back_end.model.node.ListEndNode;
import back_end.model.node.Node;
import back_end.model.node.VariableNode;
import back_end.model.robot.Robot;
import back_end.model.states.Scope;
import integration.languages.Languages;


/**
 * Generates Nodes for the TextParser Stack
 * 
 * @author Hannah Fuchshuber
 *
 */
public class NodeFactory {

    private static final String PACKAGE_RESOURCE = "resources.commandtypes.";
    private static final String PACKAGE_NODE = "back_end.model.node.";
    private static final String TYPE = "CommandTypes";

	private ResourceBundle mySyntaxResources;
	private ResourceBundle myCommandTypeResources;
	private Scope myScope;
	private CommandFactory myCommandFactory;
	private Languages myLanguage;
	private Translator myTranslator;
	
	public NodeFactory(ResourceBundle aResource, Scope aScope, Robot aRobot) {
		mySyntaxResources = aResource; 
		myCommandTypeResources = PropertyResourceBundle.getBundle(PACKAGE_RESOURCE + TYPE);
		myCommandFactory = new CommandFactory(aScope, aRobot);
		myTranslator = new Translator();
		myScope = aScope;
	}
	
	public Node makeNode(String aUserInputWord) throws UnexpectedCharacterException, UnexpectedCommandException, 
														InstantiationException, IllegalAccessException, IllegalArgumentException, 
														InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		if (Pattern.matches(mySyntaxResources.getString("Command"), aUserInputWord)) {
			try {
				String command = translateInput(aUserInputWord, myLanguage.getFileLocation());
				String type = myCommandTypeResources.getString(command);
				int inputNumber = Integer.parseInt(mySyntaxResources.getString(command));
				ICommand commandClass = null;
				if (type.equals("Branch") || type.equals("Command") || type.equals("")) {
					commandClass = (ICommandBranch) myCommandFactory.makeCommand(aUserInputWord, command);
					return (Node) Class.forName(PACKAGE_NODE + type + "Node").getConstructor(ICommand.class, int.class, Scope.class).
							newInstance(commandClass, inputNumber, myScope);
				}
			} catch (MissingResourceException e) {
				if (myScope.getVariableMap().containsVariable(aUserInputWord)) {
					CustomCommand commandClass = (CustomCommand) myCommandFactory.makeCommand("Custom", aUserInputWord);
					return (Node) Class.forName(PACKAGE_NODE + "CustomNode").getConstructor(CustomCommand.class, int.class, Scope.class).newInstance(commandClass, 1, myScope);
				} else {
					e.addSuppressed(new UnexpectedCharacterException("The syntax expression: " + aUserInputWord + " is not associated to any known syntax in this language"));
				}
			}
		}
		else {
			String command = translateInput(aUserInputWord, mySyntaxResources.getBaseBundleName()); 
			return (Node) Class.forName(PACKAGE_NODE + command + "Node").getConstructor(String.class).newInstance(aUserInputWord);
		}
		throw new UnexpectedCharacterException("The syntax expression: " + aUserInputWord + " is not associated to any known syntax in this language");
	}
	
	private String translateToVariable(String aWord) {
		return aWord.substring(1);
	}
	
	private String translateInput(String aWord, String aInputFileLocation) { 
		myTranslator.addPatterns(aInputFileLocation);
		return myTranslator.getSymbol(aWord);
	}

    public void setLanguage (Languages aLanguage) {
        myLanguage = aLanguage;
    }

}
