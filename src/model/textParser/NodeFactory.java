package model.textParser;


import java.lang.reflect.InvocationTargetException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import model.command.ICommand;
import model.exception.UnexpectedCharacterException;
import model.node.BeginBraceNode;
import model.node.ConstantNode;
import model.node.EndBraceNode;
import model.node.INode;
import model.node.VariableNode;
import model.robot.Robot;
import model.states.Scope;
import model.states.VariableState;

/**
 * Generates Nodes for the TextParser Stack
 * 
 * @author Hannah Fuchshuber
 *
 */
public class NodeFactory {
	
	private static final String PACKAGE_RESOURCE = "resource.languages.";
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
	
	public INode makeNode(String aWord) throws UnexpectedCharacterException {
		INode node = null;
		if (Pattern.matches(aWord, mySyntaxResources.getString("Variable"))) {
			node = new VariableNode(translateToVariable(aWord), myScope); 
		}
		if (Pattern.matches(aWord, mySyntaxResources.getString("Command"))) {
			String command = translateToCommand(aWord);
			String type = myCommandTypeResources.getString(command);
			if (type != "Branch" || type != "Assignment" || type != "Custom") {
				type = "Command";
			}
			int inputNumber = Integer.parseInt(mySyntaxResources.getString(command));
			try {
				ICommand commandClass = myCommandFactory.makeCommand(translateToCommand(command));
				node = (INode) Class.forName(PACKAGE_NODE + type + "Node").getConstructor(ICommand.class, int.class, Scope.class).
						newInstance(commandClass, inputNumber, myScope);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException
					| ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		if (Pattern.matches(aWord, mySyntaxResources.getString("Constant"))) {
			node = new ConstantNode(Double.parseDouble(aWord));
		}
		if (Pattern.matches(aWord, mySyntaxResources.getString("ListStart"))) {
			node = new BeginBraceNode();
		}
		if (Pattern.matches(aWord, mySyntaxResources.getString("ListEnd"))) {
			node = new EndBraceNode();
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
