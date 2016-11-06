package back_end.model.text_parser;

import java.text.MessageFormat;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import back_end.model.command.ICommand;
import back_end.model.exception.UnexpectedCharacterException;
import back_end.model.exception.UnexpectedCommandException;
import back_end.model.node.INode;
import back_end.model.node.IReadableInput;
import back_end.model.node.dummy_nodes.NullNode;
import back_end.model.node.inner_nodes.command_nodes.input_nodes.CommandDefinitionNode;
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
	
	
	/**
	 * This is a method that creates the constructor for the Node Factory, it should only be called once in the Model Controller, and 
	 * it makes an instance of all ResourceBundles and Controllers (such as Environment and Scope Controller) and a CommandFactory
	 * 
	 * @param aScopeController
	 * @param aResource
	 * @param aEnvironment
	 * @param aRobot
	 */
	public NodeFactory(ScopeController aScopeController, ResourceBundle aResource, Environment aEnvironment, IRobot aRobot) {
		mySyntaxResources = aResource; 
		myCommandTypeResources = PropertyResourceBundle.getBundle(PACKAGE_RESOURCE + PACKAGE_COMMAND + TYPE);
		myCommandFactory = new CommandFactory(aEnvironment, aRobot);
		myScopeController = aScopeController;
		myEnvironment = aEnvironment;
		myErrorMessageResources = PropertyResourceBundle.getBundle(PACKAGE_RESOURCE + PACKAGE_ERROR + ERROR);
		myNumberOfInputsResources = PropertyResourceBundle.getBundle(PACKAGE_RESOURCE + NUMBER_OF_INPUTS);
	}
	
	/**
	 * This is the central method that is called for this class, it makes a Node. We have many different Node classes so it creates the correct
	 * type of Node using reflection. 
	 * 
	 * @param aLineNumber
	 * @param aUserInputWord
	 * @param aPreviousNode
	 * @return
	 * @throws UnexpectedCharacterException
	 * @throws UnexpectedCommandException
	 */
	public INode makeNode(Integer aLineNumber, String aUserInputWord, INode aPreviousNode) throws UnexpectedCharacterException, UnexpectedCommandException {
			try {
				String generalNodeCategory = translateInput(aUserInputWord, mySyntaxResources.getBaseBundleName());
				String commandType = getCommandType(generalNodeCategory, aUserInputWord, aLineNumber, aPreviousNode);
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
				return (INode) Class.forName(packagePath + generalNodeCategory + "Node").getConstructor(ICommand.class, int.class, String.class, ScopeController.class).
						newInstance(commandClass, inputNumber, aUserInputWord, myScopeController);
			} catch (Exception e) {
				throw new UnexpectedCharacterException(MessageFormat.format(myErrorMessageResources.getString("UnexpectedCharacter"), aUserInputWord, aLineNumber), aLineNumber);
			}
	}
	
	/**
	 * Setter for the language types, allows communication with front-end.
	 * 
	 * @param aLanguage
	 */
    public void setLanguage (Languages aLanguage) {
        myLanguage = aLanguage;
    }
	
    /**
     * Private method that assists the makeNode() method by using the translator class to translate regular expressions.
     * 
     * @param aWord
     * @param aInputFileLocation
     * @return
     */
	private String translateInput(String aWord, String aInputFileLocation) { 
		Translator translator = new Translator();
		translator.addPatterns(aInputFileLocation);
		return translator.getSymbol(aWord);
	}
    
	/**
	 * Private method that assists the makeNode() method by making a using the CommandFactory to make a command class.
	 * 
	 * @param aCommandType
	 * @param aGeneralNodeCategory
	 * @param aUserInputWord
	 * @param aLineNumber
	 * @return
	 * @throws UnexpectedCommandException
	 */
    private ICommand makeCommandClass(String aCommandType, String aGeneralNodeCategory, String aUserInputWord, int aLineNumber) throws UnexpectedCommandException {
    	if (aGeneralNodeCategory.equals("Command") || aGeneralNodeCategory.equals("Variable")) {
    		return myCommandFactory.makeCommand(aUserInputWord, aCommandType, aLineNumber);
    	}
    	return null; 
    }
    
    /**
     * Private method that assists the makeNode() method by getting the file path to use in reflection.
     * 
     * @param aGeneralNodeCategory
     * @return
     */
    private String getPackagePath(String aGeneralNodeCategory) {
    	String packagePath = PACKAGE_NODE;
		switch (aGeneralNodeCategory) {
		case "DynamicReturnValue":
			return packagePath + PACKAGE_INNER_NODE;
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
    
    /**
     * Private method that assists the makeNode() method by getting the type of command that will be used in the command factory.
     * 
     * @param aGeneralNodeCategory
     * @param aUserInputWord
     * @param aLineNumber
     * @param aPreviousNode
     * @return
     * @throws UnexpectedCharacterException
     */
    private String getCommandType(String aGeneralNodeCategory, String aUserInputWord, int aLineNumber, INode aPreviousNode) throws UnexpectedCharacterException {
    	if ((aPreviousNode instanceof CommandDefinitionNode)) {
    		String aWord = String.copyValueOf(aUserInputWord.toCharArray());
    		aWord = aWord.replace(":", "");
    		myEnvironment.assignMethod(aWord, new IReadableInput[0], (IReadableInput) new NullNode());
    	}
    	if (aGeneralNodeCategory.equals("Command")) {
			String commandName = translateInput(aUserInputWord, myLanguage.getFileLocation());
			if (commandName.equals("NO MATCH")) {
				if (myEnvironment.getAllMethodNames().contains(aUserInputWord)) {
					return "Custom";
				} else {
					throw new UnexpectedCharacterException(MessageFormat.format(myErrorMessageResources.getString("UnexpectedCharacter"), aUserInputWord, aLineNumber), aLineNumber);
				}
			}
			return translateInput(aUserInputWord, myLanguage.getFileLocation());
		}
		else if (aGeneralNodeCategory.equals("Variable")) {
			return "RetrieveValue";
		} 
		return ""; 
    }
    
    
    /**
     * Private method that assists the makeNode() method by getting the number of inputs to pass into the constructor of the node.
     * 
     * @param aGeneralNodeCategory
     * @param aCommandType
     * @return
     */
    private int getInputNumber(String aGeneralNodeCategory, String aCommandType) {
    	if (aGeneralNodeCategory.equals("Command")) {
    		return Integer.parseInt(myNumberOfInputsResources.getString(aCommandType));
    	} 
    	return 0;
    }

}
