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
	private Translator myTranslator;
	private ScopeController myScopeController;
	
	public NodeFactory(ScopeController aScopeController, ResourceBundle aResource, Environment aEnvironment, IRobot aRobot) {
		mySyntaxResources = aResource; 
		myCommandTypeResources = PropertyResourceBundle.getBundle(PACKAGE_RESOURCE + TYPE);
		myCommandFactory = new CommandFactory(aEnvironment, aRobot);
		myTranslator = new Translator();
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
					inputNumber = getInputNumber(generalNodeCategory);
					aUserInputWord = translateInput(aUserInputWord, myLanguage.getFileLocation());
					commandClass = myCommandFactory.makeCommand(aUserInputWord, commandType);
					if (generalNodeCategory.equals("Command")) {
						generalNodeCategory = myCommandTypeResources.getString("aUserInputWord");
					}
				}
				return (INode) Class.forName(PACKAGE_NODE + generalNodeCategory + "Node").getConstructor(ICommand.class, int.class, String.class, ScopeController.class).
						newInstance(commandClass, inputNumber, aUserInputWord, myScopeController);
			} catch (MissingResourceException e) {
				e.addSuppressed(new UnexpectedCharacterException("The syntax expression: " + aUserInputWord + " is not associated to any known syntax in this language"));
			}
		throw new UnexpectedCharacterException("The syntax expression: " + aUserInputWord + " is not associated to any known syntax in this language");
	}
	
	private String translateInput(String aWord, String aInputFileLocation) { 
		myTranslator.addPatterns(aInputFileLocation);
		return myTranslator.getSymbol(aWord);
	}

    public void setLanguage (Languages aLanguage) {
        myLanguage = aLanguage;
    }
    
    private String getCommandType(String aGeneralNodeCategory, String aUserInputWord) {
    	String commandTypeReturn = "Custom";
		if (aGeneralNodeCategory.equals("Command")){
			aGeneralNodeCategory = myCommandTypeResources.getString(aGeneralNodeCategory);
			if (aGeneralNodeCategory != null) {
				commandTypeReturn = translateInput(aUserInputWord, myLanguage.getFileLocation());
			}
		}
		else if (aGeneralNodeCategory.equals("Variable")) {
			commandTypeReturn = "RetrieveVariable";
		} 
		return commandTypeReturn;
    }
    
    private int getInputNumber(String aGeneralNodeCategory) {
    	int inputNumberReturn = 0;
    	if (aGeneralNodeCategory.equals("Command")) inputNumberReturn = 1; 
		if (aGeneralNodeCategory != null) {
			inputNumberReturn = Integer.parseInt(mySyntaxResources.getString(aGeneralNodeCategory));
		}
		return inputNumberReturn;
    }

}
