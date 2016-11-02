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
import integration.languages.ILanguageSwitcher.Languages;


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
    private static final String NUMBER_OF_INPUTS = "syntax.NumberOfInputs";
 

	private ResourceBundle mySyntaxResources;
	private ResourceBundle myCommandTypeResources;
	private ResourceBundle myErrorMessageResources;
	private ResourceBundle myNumberOfInputsResources;
	private CommandFactory myCommandFactory;
	private Languages myLanguage;
	private ScopeController myScopeController;
	private Environment myEnvironment;
	
	public NodeFactory(ScopeController aScopeController, ResourceBundle aResource, Environment aEnvironment, IRobot aRobot) {
		mySyntaxResources = aResource; 
		myCommandTypeResources = PropertyResourceBundle.getBundle(PACKAGE_RESOURCE + PACKAGE_COMMAND + TYPE);
		myCommandFactory = new CommandFactory(aEnvironment, aRobot);
		myScopeController = aScopeController;
		myEnvironment = aEnvironment;
		myErrorMessageResources = PropertyResourceBundle.getBundle(PACKAGE_RESOURCE + PACKAGE_ERROR + ERROR);
		myNumberOfInputsResources = PropertyResourceBundle.getBundle(PACKAGE_RESOURCE + NUMBER_OF_INPUTS);
	}
	
	public INode makeNode(Integer aLineNumber, String aUserInputWord) throws UnexpectedCharacterException, UnexpectedCommandException {
			//try {
				String generalNodeCategory = translateInput(aUserInputWord, mySyntaxResources.getBaseBundleName());
				String commandType = getCommandType(generalNodeCategory, aUserInputWord, aLineNumber);
				ICommand commandClass = makeCommandClass(commandType, generalNodeCategory, aUserInputWord, aLineNumber);
				int inputNumber = 0;
				if (generalNodeCategory.equals("Variable") || generalNodeCategory.equals("Command")) {
					inputNumber = getInputNumber(generalNodeCategory, commandType);
					if (generalNodeCategory.equals("Command")) {
						generalNodeCategory = myCommandTypeResources.getString(commandType);
					}
				}
				String packagePath = getPackagePath(generalNodeCategory);
				aUserInputWord = aUserInputWord.replaceAll(":", "");
				try{
				return (INode) Class.forName(packagePath + generalNodeCategory + "Node").getConstructor(ICommand.class, int.class, String.class, ScopeController.class).
						newInstance(commandClass, inputNumber, aUserInputWord, myScopeController);
			} catch (Exception e) {
				throw new UnexpectedCharacterException(MessageFormat.format(myErrorMessageResources.getString("UnexpectedCharacter"), aUserInputWord, aLineNumber), aLineNumber);
			}
	}
	
    public void setLanguage (Languages aLanguage) {
        myLanguage = aLanguage;
    }
	
	private String translateInput(String aWord, String aInputFileLocation) { 
		Translator translator = new Translator();
		translator.addPatterns(aInputFileLocation);
		return translator.getSymbol(aWord);
	}
    
    private ICommand makeCommandClass(String aCommandType, String aGeneralNodeCategory, String aUserInputWord, int aLineNumber) throws UnexpectedCommandException {
    	if (aGeneralNodeCategory.equals("Command") || aGeneralNodeCategory.equals("Variable")) {
    		return myCommandFactory.makeCommand(aUserInputWord, aCommandType, aLineNumber);
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
    
    private String getCommandType(String aGeneralNodeCategory, String aUserInputWord, int aLineNumber) {//throws UnexpectedCharacterException {
		if (aGeneralNodeCategory.equals("Command")) {
			String commandName = translateInput(aUserInputWord, myLanguage.getFileLocation());
			if (commandName.equals("NO MATCH")) {
				//if (myEnvironment.getAllMethodNames().contains(aUserInputWord)) {
					return "Custom";
				//} else {
					//throw new UnexpectedCharacterException(MessageFormat.format(myErrorMessageResources.getString("UnexpectedCharacter"), aUserInputWord, aLineNumber), aLineNumber);
				//}
			} else {
				return translateInput(aUserInputWord, myLanguage.getFileLocation());
			}
		}
		else if (aGeneralNodeCategory.equals("Variable")) {
			return "RetrieveValue";
		} 
		return ""; 
    }
    
    private int getInputNumber(String aGeneralNodeCategory, String aCommandType) {
    	if (aGeneralNodeCategory.equals("Command")) {
    		return Integer.parseInt(myNumberOfInputsResources.getString(aCommandType));
    	} 
    	return 0;
    }

}
