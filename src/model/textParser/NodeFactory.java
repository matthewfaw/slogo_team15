package model.textParser;


import java.lang.reflect.InvocationTargetException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import model.command.ICommand;
import model.node.ConstantNode;
import model.node.INode;
import model.node.VariableNode;
import model.states.VariableState;

/**
 * Generates Nodes for the TextParser Stack
 * 
 * @author Hannah Fuchshuber
 *
 */
public class NodeFactory {
	
	private static final String PACKAGE_RESOURCE = "resource.languages.";
	private static final String PACKAGE_COMMAND = "model.command.";
	private static final String PACKAGE_NODE = "model.node.";
	private static final String LANGUAGE = "English";

	private ResourceBundle mySyntaxResources;
	private ResourceBundle myCommandTypeResources;
	private VariableState myVariableStates;
	
	public NodeFactory(ResourceBundle aResource) {
		mySyntaxResources = aResource; 
		myCommandTypeResources = PropertyResourceBundle.getBundle(PACKAGE_RESOURCE + LANGUAGE);
		myVariableStates = new VariableState();
	}
	
	public INode makeNode(String aWord) {
		INode node = null;
		if (Pattern.matches(aWord, mySyntaxResources.getString("Variable"))) {
			node = new VariableNode(translateToVariable(aWord), myVariableStates); 
		}
		if (Pattern.matches(aWord, mySyntaxResources.getString("Command"))) {
			String command = translateToCommand(aWord);
			String type = myCommandTypeResources.getString(command);
			int inputNumber = Integer.parseInt(mySyntaxResources.getString(command));
			ICommand commandClass;
			try {
				commandClass = (ICommand) Class.forName(PACKAGE_COMMAND + command + "Command").getConstructor().newInstance();
				node = (INode) Class.forName(PACKAGE_NODE + type + "Node").getConstructor(ICommand.class, int.class, VariableState.class).
						newInstance(commandClass, inputNumber, myVariableStates);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException
					| ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		if (Pattern.matches(aWord, mySyntaxResources.getString("Constant"))) {
			node = new ConstantNode(Double.parseDouble(aWord));
		}
		/*if (Pattern.matches(aWord, mySyntaxResources.getString("ListStart")) || Pattern.matches(aWord, mySyntaxResources.getString("ListEnd"))) {
			node = new BeginBraceNode(aWord);
		}*/
		return node;
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
