package back_end.model.text_parser;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Stack;

import back_end.model.exception.EmptyInputException;
import back_end.model.exception.UnexpectedCharacterException;
import back_end.model.exception.UnexpectedCommandException;
import back_end.model.node.INode;
import back_end.model.robot.IRobot;
import back_end.model.states.Environment;
import back_end.model.states.ScopeController;
import integration.languages.ILanguageSwitcher.Languages;


/**
 * Class that creates a stack of Nodes based off what the text input is.
 * 
 * @author Hannah Fuchshuber
 *
 */

public class TextParser {

    private static final String PACKAGE = "resources.syntax.";
    private static final String SYNTAX = "Syntax";

    private Stack<INode> myNodes;
    private ResourceBundle mySyntaxResources;
    private NodeFactory myFactory;
    private Languages myLanguage;
    private Environment myEnvironment;

    public TextParser (ScopeController aScopeController, Environment aEnvironment, IRobot aRobot) {
        mySyntaxResources = PropertyResourceBundle.getBundle(PACKAGE + SYNTAX);
        myLanguage = Languages.DEFAULT;
        myEnvironment = aEnvironment;
        myFactory = new NodeFactory(aScopeController, mySyntaxResources, aEnvironment, aRobot);
        setLanguage(myLanguage);
    }

    /**
     * Getter for getting the stack of parsed nodes
     * 
     * @return
     * @throws UnexpectedCommandException
     * @throws UnexpectedCharacterException
     * @throws ClassNotFoundException
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws EmptyInputException
     */
    public Stack<INode> getNodeStack (String aString) throws EmptyInputException, UnexpectedCharacterException, UnexpectedCommandException  {
        myNodes = new Stack<INode>();
		createNodes(aString);
        if (myNodes.isEmpty())
            throw new EmptyInputException("No input!");
        return myNodes;
    }

    public void setLanguage (Languages aLanguage) {
        myFactory.setLanguage(aLanguage);
    }

    /**
     * This method will create the stack containing the Nodes needed to create the tree
     * 
     * @throws UnexpectedCommandException
     * @throws UnexpectedCharacterException
     * @throws ClassNotFoundException
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private void createNodes (String aText) throws UnexpectedCharacterException, UnexpectedCommandException {
        Map<Integer, List<String>> wordMap = makeExecutableList(aText);
        for (Integer lineNumber : wordMap.keySet()) {
        	INode previousNode = null;
        	for (String word : wordMap.get(lineNumber)) {
        		if (!myNodes.isEmpty()) previousNode = myNodes.peek();
        		myNodes.push(getNode(lineNumber, word, previousNode));
        	}
        }
        myEnvironment.clearMethods();
    }

    /**
     * This method generates the Node appropriate for the word parsed in
     * @param lineNumber 
     * 
     * @param word
     * @return
     * @throws UnexpectedCommandException
     * @throws UnexpectedCharacterException
     * @throws ClassNotFoundException
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private INode getNode (Integer aLineNumber, String aWord, INode aNode) throws UnexpectedCharacterException, UnexpectedCommandException {
        return myFactory.makeNode(aLineNumber, aWord, aNode);
    }

    /**
     * Check for whitespace lines and remove comments
     * 
     * @param text
     * @return
     */
    private Map<Integer, List<String>> makeExecutableList (String aText) {
        String[] wordList = aText.split(mySyntaxResources.getString("Line"));
        Map<Integer, List<String>> executableMap = new HashMap<Integer, List<String>>();
        for (int i = 0; i < wordList.length; i++) {
            if (wordList[i].length() > 0 &&
                wordList[i].charAt(0) != mySyntaxResources.getString("Comment").charAt(0)) {
                String[] temp = wordList[i].split(mySyntaxResources.getString("Space"));
                List<String> temporaryStrings = new ArrayList<String>();
                for (int j = 0; j < temp.length; j++) {
                    temporaryStrings.add(temp[j]);
                }
                executableMap.put(i + 1, temporaryStrings);
            }
        }
        return executableMap;
    }

}
