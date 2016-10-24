package back_end.model.textParser;

import java.util.ArrayList;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Stack;

import back_end.model.exception.UnexpectedCharacterException;
import back_end.model.node.Node;
import back_end.model.robot.Robot;
import back_end.model.states.Scope;



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
	 * Getter for getting the stack of parsed nodes
	 * @return
	 */
	public Stack<Node> getNodeStack(String aString) {
		createNodes(aString);
		return myNodes;
	}
	
	/**
	 * This method will create the stack containing the Nodes needed to create the tree
	 */
	private void createNodes(String aText) {
		ArrayList<String> wordList = makeExecutableList(aText);
		for (String word: wordList) {
			myNodes.push(getNode(word));
		}
//		Collections.reverse(myNodes);
	}
	
	/**
	 * This method generates the Node appropriate for the word parsed in
	 * @param word
	 * @return
	 */
	private Node getNode(String aWord) {
		try {
			return myFactory.makeNode(aWord);
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
	private ArrayList<String> makeExecutableList(String aText) {
		String[] wordList = aText.split(mySyntaxResources.getString("Line"));
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
