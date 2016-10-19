package model.textParser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Stack;

import javafx.scene.Node;



/**
 * Class that creates a stack of Nodes based off what the text input is.
 * 
 * @author Hannah Fuchshuber
 *
 */

public class TextParser implements Iterable<Node> {
	
	private static final String PACKAGE = "resource.languages/";
	private static final String LANGUAGE = "Syntax";
	
	private Stack<Node> myNodes;
	private ResourceBundle myResources;

	public TextParser(){
		//TODO 
		myNodes = new Stack<Node>();
		myResources = PropertyResourceBundle.getBundle(PACKAGE + LANGUAGE);
	}
	
	/**
	 * This method will create the stack that then can be iteratored through
	 */
	public void createNodes(String text) {
		ArrayList<String> wordList = (ArrayList<String>) makeExecutableList(text);
		for (int i = 0; i < wordList.size(); i++) {
			myNodes.add(getNode(wordList.get(i)));
		}
	}
	
	/**
	 * This method generates the Node appropriate for the word parsed in
	 * @param word
	 * @return
	 */
	private Node getNode(String word) {
		NodeFactory factory = new NodeFactory(word, myResources); 
		return factory.makeNode();
	}
	
	/**
	 * Check for whitespace lines and remove comments
	 * @param text
	 * @return
	 */
	private Collection<String> makeExecutableList(String text) {
		String[] wordList = text.split(myResources.getString("Line"));
		ArrayList<String> executableList = new ArrayList<String>();
		for (int i = 0; i < wordList.length; i++) {
			if (wordList[i].charAt(0) != myResources.getString("Comment").charAt(0) && wordList[i].length() > 1) {
				String[] temp = wordList[i].split(myResources.getString("Space"));
				for (int j = 0; j < temp.length; j++) {
					executableList.add(temp[j]);
				}
			}
		}
		return executableList; 
	}
	
	
	/**
	 * This iterator destroys the stack
	 */
	public Iterator<Node> iterator() {
        Iterator<Node> iterator = new Iterator<Node>() {

        	private int currentIndex = 0;

            @Override
            public boolean hasNext() {
            	return currentIndex < commandSize() && myNodes.peek() != null;
            }

            @Override
            public Node next() {
                return myNodes.pop();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return iterator;
    }
	
	/**
	 * This method returns the size of the stack
	 * @return int
	 */
	private int commandSize() {
		return myNodes.size();
	}
	
	
}
