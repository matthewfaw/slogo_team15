package back_end.model.text_parser;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import back_end.model.command.ICommand;
import back_end.model.exception.UnexpectedCharacterException;
import back_end.model.exception.UnexpectedCommandException;
import back_end.model.node.INode;
import back_end.model.robot.IRobot;
import back_end.model.states.Environment;
import back_end.model.states.ScopeController;
import integration.languages.Languages;


/**
 * Generates Nodes for the TextParser Stack
 * 
 * @author Hannah Fuchshuber
 *
 */
public class NodeFactory {

    private static final String PACKAGE_RESOURCE = "resources.";
    private static final String PACKAGE_COMMAND = "commandtypes.";
    private static final String PACKAGE_ERROR = "errormessages.";
    private static final String PACKAGE_NODE = "back_end.model.node.";
    private static final String PACKAGE_INNER_NODE = "inner_nodes.command_nodes.";
    private static final String PACKAGE_INPUT_NODE = "input_nodes.";
    private static final String PACKAGE_BRANCH_NODE = "branching_nodes.";
    private static final String PACKAGE_LEAF_NODE = "leaf_nodes.";
    private static final String PACKAGE_DUMMY_NODE = "dummy_nodes.";
    private static final String TYPE = "CommandTypes";
    private static final String ERROR = "ErrorMessages";

	private ResourceBundle mySyntaxResources;
	private ResourceBundle myCommandTypeResources;
	private ResourceBundle myErrorMessageResources;
	private CommandFactory myCommandFactory;
	private Languages myLanguage;
	private ScopeController myScopeController;
	
	public NodeFactory(ScopeController aScopeController, ResourceBundle aResource, Environment aEnvironment, IRobot aRobot) {
		mySyntaxResources = aResource; 
		myCommandTypeResources = PropertyResourceBundle.getBundle(PACKAGE_RESOURCE + PACKAGE_COMMAND + TYPE);
		myCommandFactory = new CommandFactory(aEnvironment, aRobot);
		myScopeController = aScopeController;
		myErrorMessageResources = PropertyResourceBundle.getBundle(PACKAGE_RESOURCE + PACKAGE_ERROR + ERROR);
	}
	
	public INode makeNode(String aUserInputWord) throws UnexpectedCharacterException, UnexpectedCommandException, 
														InstantiationException, IllegalAccessException, IllegalArgumentException, 
														InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
			try {
				String generalNodeCategory = translateInput(aUserInputWord, mySyntaxResources.getBaseBundleName());
				String commandType = getCommandType(generalNodeCategory, aUserInputWord);
				ICommand commandClass = makeCommandClass(commandType, generalNodeCategory, aUserInputWord);
				int inputNumber = getInputNumber(commandType);
				if (generalNodeCategory.equals("Command")) {
					generalNodeCategory = myCommandTypeResources.getString(commandType);
				}
				String packagePath = getPackagePath(generalNodeCategory);
				return (INode) Class.forName(packagePath + generalNodeCategory + "Node").getConstructor(ICommand.class, int.class, String.class, ScopeController.class).
						newInstance(commandClass, inputNumber, aUserInputWord, myScopeController);
			} catch (MissingResourceException e) {
				e.addSuppressed(new UnexpectedCharacterException(MessageFormat.format(myErrorMessageResources.getString("UnexpectedCharacter"), aUserInputWord)));
			}
		throw new UnexpectedCharacterException(MessageFormat.format(myErrorMessageResources.getString("UnexpectedCharacter"), aUserInputWord));
	}
	
    public void setLanguage (Languages aLanguage) {
        myLanguage = aLanguage;
    }
	
	private String translateInput(String aWord, String aInputFileLocation) { 
		Translator translator = new Translator();
		translator.addPatterns(aInputFileLocation);
		return translator.getSymbol(aWord);
	}
    
    private ICommand makeCommandClass(String aCommandType, String aGeneralNodeCategory, String aUserInputWord) throws InstantiationException, 
    																						IllegalAccessException, IllegalArgumentException, 
    																						InvocationTargetException, NoSuchMethodException, 
    																						SecurityException, ClassNotFoundException {
    	if (aGeneralNodeCategory.equals("Command") || aGeneralNodeCategory.equals("Variable")) {
    		return myCommandFactory.makeCommand(aUserInputWord, aCommandType);
    	}
    	return null; 
    }
    
    private String getPackagePath(String aGeneralNodeCategory) {
    	String packagePath = PACKAGE_NODE;
		switch (aGeneralNodeCategory) {
			case "InputCommand": 
			case "CommandDefinition":
				return packagePath + PACKAGE_INNER_NODE + PACKAGE_INPUT_NODE;
			case "Custom":
			case "ControlFlow":
				return packagePath + PACKAGE_INNER_NODE + PACKAGE_BRANCH_NODE;
			case "Constant":
			case "Variable":
				return packagePath + PACKAGE_LEAF_NODE;
			default:
				return packagePath + PACKAGE_DUMMY_NODE;
		}
    }
    
    private String getCommandType(String aGeneralNodeCategory, String aUserInputWord) {
		if (aGeneralNodeCategory.equals("Command")) {
			String commandName = translateInput(aUserInputWord, myLanguage.getFileLocation());
			if (commandName.equals("NO MATCH")) {
				return "Custom";
			} else {
				return translateInput(aUserInputWord, myLanguage.getFileLocation());
			}
		}
		else if (aGeneralNodeCategory.equals("Variable")) {
			return "RetrieveValue";
		} 
		return null; 
    }
    
    private int getInputNumber(String aGeneralNodeCategory) {
    	if (aGeneralNodeCategory.equals("Command")) return Integer.parseInt(mySyntaxResources.getString(aGeneralNodeCategory)); 
    	return 0;
		
    }

}
