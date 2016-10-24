package back_end.model.textParser;


import java.lang.reflect.InvocationTargetException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import back_end.model.command.ICommand;
import back_end.model.exception.UnexpectedCharacterException;
import back_end.model.node.BeginBraceNode;
import back_end.model.node.ConstantNode;
import back_end.model.node.EndBraceNode;
import back_end.model.node.Node;
import back_end.model.node.VariableNode;
import back_end.model.robot.Robot;
import back_end.model.states.Scope;

/**
 * Generates Nodes for the TextParser Stack
 * 
 * @author Hannah Fuchshuber
 *
 */
public class NodeFactory {
	
	private static final String PACKAGE_RESOURCE = "resources.languages.";
	private static final String PACKAGE_NODE = "model.node.";
	private static final String LANGUAGE = "English";
	private static final String TYPE = "CommandTypes";

	private ResourceBundle mySyntaxResources;
	private ResourceBundle myCommandTypeResources;
	private Scope myScope;
	private CommandFactory myCommandFactory;
	
	public NodeFactory(ResourceBundle aResource, Scope aScope, Robot aRobot) {
		mySyntaxResources = aResource; 
		myCommandTypeResources = PropertyResourceBundle.getBundle(PACKAGE_RESOURCE + TYPE);
		myCommandFactory = new CommandFactory(aScope, aRobot);
		myScope = aScope;
	}
	
	public Node makeNode(String aWord) throws UnexpectedCharacterException {
		if (Pattern.matches(mySyntaxResources.getString("Variable"), aWord)) {
			return new VariableNode(translateToVariable(aWord), myScope); 
		}
		else if (Pattern.matches(mySyntaxResources.getString("Command"), aWord)) {
			String command = translateToCommand(aWord);
			String type = myCommandTypeResources.getString(command);
			if (type != "Branch" || type != "Assignment" || type != "Custom") {
				type = "Command";
			}
			int inputNumber = Integer.parseInt(mySyntaxResources.getString(command));
			try {
				ICommand commandClass = myCommandFactory.makeCommand(translateToCommand(command));
				return (Node) Class.forName(PACKAGE_NODE + type + "Node").getConstructor(ICommand.class, int.class, Scope.class).
						newInstance(commandClass, inputNumber, myScope);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException
					| ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		else if (Pattern.matches(mySyntaxResources.getString("Constant"), aWord)) {
			return new ConstantNode(Double.parseDouble(aWord));
		}
		else if (Pattern.matches(mySyntaxResources.getString("ListStart"), aWord)) {
			return new BeginBraceNode();
		}
		else if (Pattern.matches(mySyntaxResources.getString("ListEnd"), aWord)) {
			return new EndBraceNode();
		}
		throw new UnexpectedCharacterException();
	}
	
	private String translateToVariable(String aWord) {
		return aWord.substring(1);
	}
	
	private String translateToCommand(String aWord) { 
		CommandTranslator parse = new CommandTranslator();
		parse.addPatterns(PACKAGE_RESOURCE + LANGUAGE);
		return parse.getSymbol(aWord);
	}
	
	
}
