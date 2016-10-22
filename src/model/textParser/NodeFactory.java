package model.textParser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.scene.Node;
import model.command.ICommand;

/**
 * Generates Nodes for the TextParser Stack
 * 
 * @author Hannah Fuchshuber
 *
 */
public class NodeFactory {
	
	private static final String PACKAGE_COMMAND = "model.command.";
	private static final String PACKAGE_RESOURCE = "resource.languages.";
	private static final String LANGUAGE = "English";

	private String myWord;
	private ResourceBundle mySyntaxResources;
	
	public NodeFactory(String aWord, ResourceBundle aResource) {
		myWord = aWord;
		mySyntaxResources = aResource; 
	}
	
	public Node makeNode() {
		Node node;
		if (Pattern.matches(myWord, mySyntaxResources.getString("Variable"))) {
			node = new VariableNode(translateToVariable(myWord)); 
		}
		if (Pattern.matches(myWord, mySyntaxResources.getString("Command"))) {
			String command = translateToCommand(myWord);
			int inputNumber = Integer.parseInt(mySyntaxResources.getString(command));
			if (inputNumber < 0) {
				node = new BranchNode(command);
			} else {
				ICommand commandClass = (ICommand) Class.forName(PACKAGE_COMMAND + command + "Command").getConstructor().newInstance();
				node = new CommandNode(commandClass, inputNumber);
			}
		}
		if (Pattern.matches(myWord, mySyntaxResources.getString("Constant"))) {
			node = new ConstantNode(Double.parseDouble(myWord));
		}
		if (Pattern.matches(myWord, mySyntaxResources.getString("ListStart")) || Pattern.matches(myWord, mySyntaxResources.getString("ListEnd"))) {
			node = new BranchNode(myWord);
		}
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
