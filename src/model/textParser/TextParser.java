package model.textParser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Stack;

import model.exception.UnexpectedCharacterException;
import model.node.Node;
import model.robot.Robot;
import model.states.Scope;



/**
 * Class that creates a stack of Nodes based off what the text input is.
 * 
 * @author Hannah Fuchshuber
 *
 */

public class TextParser {
	
	private static final String PACKAGE = "resources.languages.";
	private static final String LANGUAGE = "Syntax";
	
	private Stack<Node> myNodes;
	private ResourceBundle mySyntaxResources;
	private NodeFactory myFactory;

	public TextParser(Scope aScope, Robot aRobot){
		myNodes = new Stack<Node>();
		mySyntaxResources = PropertyResourceBundle.getBundle(PACKAGE + LANGUAGE);
		myFactory = new NodeFactory(mySyntaxResources, aScope, aRobot); 
	}
	
	/**
	 * This method will create the stack containing the Nodes needed to create the tree
	 */
	public void createNodes(String text) {
		ArrayList<String> wordList = makeExecutableList(text);
		for (String word: wordList) {
			myNodes.add(getNode(word));
		}
	}
	
	/**
	 * Getter for getting the stack of parsed nodes
	 * @return
	 */
	public Collection<Node> getNodeStack() {
		return myNodes;
	}
	
	/**
	 * This method generates the Node appropriate for the word parsed in
	 * @param word
	 * @return
	 */
	private Node getNode(String word) {
		try {
			return myFactory.makeNode(word);
		} catch (UnexpectedCharacterException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Check for whitespace lines and remove comments
	 * @param text
	 * @return
	 */
	private ArrayList<String> makeExecutableList(String text) {
		String[] wordList = text.split(mySyntaxResources.getString("Line"));
		ArrayList<String> executableList = new ArrayList<String>();
		for (int i = 0; i < wordList.length; i++) {
			if (wordList[i].charAt(0) != mySyntaxResources.getString("Comment").charAt(0) && wordList[i].length() > 0) {
				String[] temp = wordList[i].split(mySyntaxResources.getString("Space"));
				for (int j = 0; j < temp.length; j++) {
					executableList.add(temp[j]);
				}
			}
		}
		return executableList; 
	}
	
	
}
