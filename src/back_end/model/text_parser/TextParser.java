package back_end.model.text_parser;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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

    public TextParser (ScopeController aScopeController, Environment aEnvironment, IRobot aRobot) {
        mySyntaxResources = PropertyResourceBundle.getBundle(PACKAGE + SYNTAX);
        myLanguage = Languages.DEFAULT;
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
    public Stack<INode> getNodeStack (String aString) throws InstantiationException,
                                                     IllegalAccessException,
                                                     IllegalArgumentException,
                                                     InvocationTargetException,
                                                     NoSuchMethodException, SecurityException,
                                                     ClassNotFoundException,
                                                     UnexpectedCharacterException,
                                                     UnexpectedCommandException,
                                                     EmptyInputException {
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
    private void createNodes (String aText) throws InstantiationException, IllegalAccessException,
                                            IllegalArgumentException, InvocationTargetException,
                                            NoSuchMethodException, SecurityException,
                                            ClassNotFoundException, UnexpectedCharacterException,
                                            UnexpectedCommandException {
        ArrayList<String> wordList = makeExecutableList(aText);
        for (String word : wordList) {
            myNodes.push(getNode(word));
        }
    }

    /**
     * This method generates the Node appropriate for the word parsed in
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
    private INode getNode (String aWord) throws InstantiationException, IllegalAccessException,
                                        IllegalArgumentException, InvocationTargetException,
                                        NoSuchMethodException, SecurityException,
                                        ClassNotFoundException, UnexpectedCharacterException,
                                        UnexpectedCommandException {
        return myFactory.makeNode(aWord);
    }

    /**
     * Check for whitespace lines and remove comments
     * 
     * @param text
     * @return
     */
    private ArrayList<String> makeExecutableList (String aText) {
        String[] wordList = aText.split(mySyntaxResources.getString("Line"));
        ArrayList<String> executableList = new ArrayList<String>();
        for (int i = 0; i < wordList.length; i++) {
            if (wordList[i].length() > 0 &&
                wordList[i].charAt(0) != mySyntaxResources.getString("Comment").charAt(0)) {
                String[] temp = wordList[i].split(mySyntaxResources.getString("Space"));
                for (int j = 0; j < temp.length; j++) {
                    executableList.add(temp[j]);
                }
            }
        }
        return executableList;
    }

}
