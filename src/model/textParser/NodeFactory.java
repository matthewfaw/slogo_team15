package model.textParser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.scene.Node;

/**
 * Generates Nodes for the TextParser Stack
 * 
 * @author Hannah Fuchshuber
 *
 */
public class NodeFactory {
	
	private static final String PACKAGE = "resource.languages/";
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
				node = new CommandNode(command, inputNumber);
			}
		}
		if (Pattern.matches(myWord, mySyntaxResources.getString("Constant"))) {
			node = new ConstantNode(Double.parseDouble(myWord));
		}
		return node;
	}
	
	private String translateToVariable(String aWord) {
		return aWord.substring(1);
	}
	
	private String translateToCommand(String aWord) { 
		CommandTranslator parse = new CommandTranslator();
		parse.addPatterns(PACKAGE + LANGUAGE);
		return parse.getSymbol(aWord);
	}
	
	
}
