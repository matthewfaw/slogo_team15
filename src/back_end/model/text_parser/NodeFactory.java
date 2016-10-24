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
import back_end.model.node.BeginBraceNode;
import back_end.model.node.ConstantNode;
import back_end.model.node.EndBraceNode;
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
	
	public NodeFactory(ResourceBundle aResource, Scope aScope, Robot aRobot) {
		mySyntaxResources = aResource; 
		myCommandTypeResources = PropertyResourceBundle.getBundle(PACKAGE_RESOURCE + TYPE);
		myCommandFactory = new CommandFactory(aScope, aRobot);
		myScope = aScope;
	}
	
	public Node makeNode(String aWord) throws UnexpectedCharacterException, UnexpectedCommandException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		if (Pattern.matches(mySyntaxResources.getString("Variable"), aWord)) {
			return new VariableNode(translateToVariable(aWord), myScope); 
		}
		else if (Pattern.matches(mySyntaxResources.getString("Command"), aWord)) {
			try {
				String command = translateToCommand(aWord);
				String type = myCommandTypeResources.getString(command);
				int inputNumber = Integer.parseInt(mySyntaxResources.getString(command));
				ICommand commandClass = null;
				if (type.equals("Branch")) {
					commandClass = (ICommandBranch) myCommandFactory.makeCommand(aWord, command);
					return (Node) Class.forName(PACKAGE_NODE + type + "Node").getConstructor(ICommandBranch.class, int.class, Scope.class).
							newInstance(commandClass, inputNumber, myScope);
				} else if (type.equals("To")) {
					commandClass = myCommandFactory.makeCommand(aWord, command);
					return (Node) Class.forName(PACKAGE_NODE + type + "Node").getConstructor(ICommand.class, int.class, Scope.class).
							newInstance(commandClass, inputNumber, myScope);
				} else {
					type = "Command";
					commandClass = myCommandFactory.makeCommand(aWord, command);
					return (Node) Class.forName(PACKAGE_NODE + type + "Node").getConstructor(ICommand.class, int.class, Scope.class).
							newInstance(commandClass, inputNumber, myScope);
				}
			} catch (MissingResourceException e) {
				if (!myScope.getVariableMap().containsVariable(aWord)) {
					CustomCommand commandClass = (CustomCommand) myCommandFactory.makeCommand("Custom", aWord);
					return (Node) Class.forName(PACKAGE_NODE + "CustomNode").getConstructor(CustomCommand.class, int.class, Scope.class).newInstance(commandClass, 1, myScope);
				} else {
					e.addSuppressed(new UnexpectedCharacterException("The syntax expression: " + aWord + " is not associated to any known syntax in this language"));
				}
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
		throw new UnexpectedCharacterException("The syntax expression: " + aWord + " is not associated to any known syntax in this language");
	}
	
	private String translateToVariable(String aWord) {
		return aWord.substring(1);
	}
	
	private String translateToCommand(String aWord) { 
		CommandTranslator parse = new CommandTranslator();
		parse.addPatterns(myLanguage.getFileLocation());
		return parse.getSymbol(aWord);
	}

	public void setLanguage(Languages aLanguage) {
		myLanguage = aLanguage;
	}

	
	
}
