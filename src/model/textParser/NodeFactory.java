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
	private static final String SYNTAX = "Syntax";

	private String myWord;
	private ResourceBundle myResources;
	
	public NodeFactory(String aWord, ResourceBundle aResource) {
		//TODO
		myWord = aWord;
		myResources = aResource; 
	}
	
	public Node makeNode() {
		Node node;
		if (Pattern.matches(myWord, myResources.getString("Variable"))) {
			node = new VariableNode(translateToVariable(myWord)); 
		}
		if (Pattern.matches(myWord, myResources.getString("Command"))) {
			node = new CommandNode(translateToCommand(myWord));
		}
		if (Pattern.matches(myWord, myResources.getString("Constant"))) {
			node = new ConstantNode(myWord);
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
