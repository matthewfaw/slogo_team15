package model.textParser;


import java.lang.reflect.InvocationTargetException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import model.command.ICommand;
import model.command.ICommandBranch;
import model.exception.UnexpectedCharacterException;
import model.exception.UnexpectedCommandException;
import model.node.BeginBraceNode;
import model.node.ConstantNode;
import model.node.EndBraceNode;
import model.node.Node;
import model.node.VariableNode;
import model.robot.Robot;
import model.states.Scope;

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
			int inputNumber = Integer.parseInt(mySyntaxResources.getString(command));
			ICommand commandClass = null;
			if (type != "Branch" || type != "Assignment" || type != "Custom") {
				type = "Command";
				try {
					commandClass = myCommandFactory.makeCommand(translateToCommand(command));
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException | ClassNotFoundException
						| UnexpectedCommandException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					commandClass = (ICommandBranch) myCommandFactory.makeCommand(translateToCommand(command));
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException | ClassNotFoundException
						| UnexpectedCommandException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				return (Node) Class.forName(PACKAGE_NODE + type + "Node").getConstructor(ICommand.class, int.class, Scope.class).
						newInstance(commandClass, inputNumber, myScope);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException | ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
