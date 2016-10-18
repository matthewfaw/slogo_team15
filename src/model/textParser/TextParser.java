package model.textParser;

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
	public void createNodes() {
		//TODO
	}
	
	private void checkValidCommand() {
		//TODO
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
