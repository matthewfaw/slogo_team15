package back_end.model.text_parser;

import java.lang.reflect.InvocationTargetException;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
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
	private Environment myEnvironment;
	private CommandFactory myCommandFactory;
	private Languages myLanguage;
	private Translator myTranslator;
	private ScopeController myScopeController;
	
	public NodeFactory(ScopeController aScopeController, ResourceBundle aResource, Environment aEnvironment, IRobot aRobot) {
		mySyntaxResources = aResource; 
		myCommandTypeResources = PropertyResourceBundle.getBundle(PACKAGE_RESOURCE + TYPE);
		myCommandFactory = new CommandFactory(aEnvironment, aRobot);
		myTranslator = new Translator();
		myEnvironment = aEnvironment;
		myScopeController = aScopeController;
	}
	
	public INode makeNode(String aUserInputWord) throws UnexpectedCharacterException, UnexpectedCommandException, 
														InstantiationException, IllegalAccessException, IllegalArgumentException, 
														InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
			try {
				String fileLocation = getFileLocation(aUserInputWord);
				String translatedInput = translateInput(aUserInputWord, fileLocation);
				int inputNumber = 0;
				ICommand commandClass = null;
				if (Pattern.matches(mySyntaxResources.getString("Command"), translatedInput)) {
					translatedInput = myCommandTypeResources.getString(translatedInput);
					if (translatedInput.equals("Branch") || translatedInput.equals("Command") || translatedInput.equals("")) {
						inputNumber = Integer.parseInt(mySyntaxResources.getString(translatedInput));
					} 
					else if (myEnvironment.getVariableKeySet().contains(aUserInputWord)) {
						inputNumber = 1; 
						translatedInput = "Custom";
					}
					commandClass = myCommandFactory.makeCommand(aUserInputWord, translatedInput);
				}
				return (INode) Class.forName(PACKAGE_NODE + translatedInput + "Node").getConstructor(ICommand.class, int.class, String.class, ScopeController.class).
						newInstance(commandClass, inputNumber, aUserInputWord, myScopeController);
			} catch (MissingResourceException e) {
				e.addSuppressed(new UnexpectedCharacterException("The syntax expression: " + aUserInputWord + " is not associated to any known syntax in this language"));
			}
		throw new UnexpectedCharacterException("The syntax expression: " + aUserInputWord + " is not associated to any known syntax in this language");
	}
	
	private String getFileLocation(String aUserInputWord) {
		String fileLocation = myLanguage.getFileLocation();
		if (Pattern.matches(aUserInputWord, mySyntaxResources.getString("Variable"))) {
			fileLocation = mySyntaxResources.getBaseBundleName();
		}
		return fileLocation;
	}
	
	private String translateInput(String aWord, String aInputFileLocation) { 
		myTranslator.addPatterns(aInputFileLocation);
		return myTranslator.getSymbol(aWord);
	}

    public void setLanguage (Languages aLanguage) {
        myLanguage = aLanguage;
    }

}
