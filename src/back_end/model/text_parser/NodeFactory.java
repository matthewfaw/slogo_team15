package back_end.model.text_parser;

import java.lang.reflect.InvocationTargetException;
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

    private static final String PACKAGE_RESOURCE = "resources.commandtypes.";
    private static final String PACKAGE_NODE = "back_end.model.node.";
    private static final String TYPE = "CommandTypes";

	private ResourceBundle mySyntaxResources;
	private ResourceBundle myCommandTypeResources;
	private CommandFactory myCommandFactory;
	private Languages myLanguage;
	private ScopeController myScopeController;
	
	public NodeFactory(ScopeController aScopeController, ResourceBundle aResource, Environment aEnvironment, IRobot aRobot) {
		mySyntaxResources = aResource; 
		myCommandTypeResources = PropertyResourceBundle.getBundle(PACKAGE_RESOURCE + TYPE);
		myCommandFactory = new CommandFactory(aEnvironment, aRobot);
		myScopeController = aScopeController;
	}
	
	public INode makeNode(String aUserInputWord) throws UnexpectedCharacterException, UnexpectedCommandException, 
														InstantiationException, IllegalAccessException, IllegalArgumentException, 
														InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
			try {
				String generalNodeCategory = translateInput(aUserInputWord, mySyntaxResources.getBaseBundleName());
				ICommand commandClass = null;
				int inputNumber = 0;
				if (generalNodeCategory.equals("Command") || generalNodeCategory.equals("Variable")) {
					String commandType = getCommandType(generalNodeCategory, aUserInputWord);
					if (generalNodeCategory.equals("Command")) {
						inputNumber = getInputNumber(commandType);
						//aUserInputWord = translateInput(aUserInputWord, myLanguage.getFileLocation());
					}
					commandClass = myCommandFactory.makeCommand(aUserInputWord, commandType);
					if (generalNodeCategory.equals("Command")) {
						generalNodeCategory = myCommandTypeResources.getString(commandType);
					}
				}
				//XXX: Refactor this ish
				String packagePath = PACKAGE_NODE;
				switch (generalNodeCategory) {
					case "InputCommand": 
						packagePath += "inner_nodes.command_nodes.input_nodes.";
						break;
					case "CommandDefinition":
						packagePath += "inner_nodes.command_nodes.branching_nodes.";
						break;
					case "ControlFlow":
						packagePath += "inner_nodes.command_nodes.branching_nodes.";
						break;
					case "Constant":
						packagePath += "leaf_nodes.";
						break;
					case "Variable":
						packagePath += "leaf_nodes.";
						break;
					default:
						packagePath += "dummy_nodes.";
				}
				return (INode) Class.forName(packagePath + generalNodeCategory + "Node").getConstructor(ICommand.class, int.class, String.class, ScopeController.class).
						newInstance(commandClass, inputNumber, aUserInputWord, myScopeController);
			} catch (MissingResourceException e) {
				e.addSuppressed(new UnexpectedCharacterException("The syntax expression: " + aUserInputWord + " is not associated to any known syntax in this language"));
			}
		throw new UnexpectedCharacterException("The syntax expression: " + aUserInputWord + " is not associated to any known syntax in this language");
	}
	
	private String translateInput(String aWord, String aInputFileLocation) { 
		Translator translator = new Translator();
		translator.addPatterns(aInputFileLocation);
		return translator.getSymbol(aWord);
	}

    public void setLanguage (Languages aLanguage) {
        myLanguage = aLanguage;
    }
    
    private String getCommandType(String aGeneralNodeCategory, String aUserInputWord) {
    	String commandTypeReturn = "Custom";
		if (aGeneralNodeCategory.equals("Command")){
			//aGeneralNodeCategory = myCommandTypeResources.getString(aGeneralNodeCategory);
			//if (aGeneralNodeCategory != null) {
				commandTypeReturn = translateInput(aUserInputWord, myLanguage.getFileLocation());
			//}
		}
		else if (aGeneralNodeCategory.equals("Variable")) {
			commandTypeReturn = "RetrieveValue";
		} 
		return commandTypeReturn;
    }
    
    private int getInputNumber(String aGeneralNodeCategory) {
		if (aGeneralNodeCategory == null) {
			// Custom command
			//XXX Move to resource file
			return 1;
		} else {
			return Integer.parseInt(mySyntaxResources.getString(aGeneralNodeCategory)); 
		}
		
    }

}
