package back_end.model.syntax_tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.INode;
import back_end.model.node.dummy_nodes.ListEndNode;
import back_end.model.node.dummy_nodes.ListStartNode;
import back_end.model.node.inner_nodes.command_nodes.AbstractCommandNode;
import back_end.model.node.inner_nodes.list_nodes.ListNode;

/**
 * The purpose of this class is to handle the construction of the Abstract Syntax tree
 * and to manage the root node of the tree.  
 * This class is dependent upon the TextParser class's validly constructing node objects
 * from the text sent from the frontend, and passing those objects in a stack, where the 
 * top of the stack starts with the last expression in the input string.
 * This construction assumes that the user has typed valid instructions with a valid number
 * of inputs, since the tree will be invalidly constructed invalidly if inputs are invalid
 * 
 * Once the Input node stack has been created, the tree is constructed by:
 * AbstractSyntaxTree ast = new AbstractSyntaxTree(inputNodeStack);
 * The root node of the tree may be accessed by calling:
 * ast.getRoot();
 * within this package
 * @author matthewfaw
 *
 */
public class AbstractSyntaxTree {
	private ListNode myRoot;
	
	/**
	 * Constructs the AbstractSyntaxTree, given a stack of nodes with which the tree can be
	 * constructed 
	 * Throws an invalid node usage exception if one of the methods in the construction performs 
	 * improper access to one of the node class's properties. This error message is primarily
	 * used to debug the node construction
	 * @param aNodeStack
	 * @throws InvalidNodeUsageException
	 */
	public AbstractSyntaxTree(Stack<INode> aNodeStack) throws InvalidNodeUsageException
	{
		myRoot = constructTree(aNodeStack);
	}
	/**
	 * A method to get the root node of the AST so that it can be traversed
	 * @return the Root node of the AST
	 */
	ListNode getRoot()
	{
		return myRoot;
	}
	
	/**
	 * The gateway method used to construct the AST
	 * @param aNodeStack
	 * @return the root node of the fully constructed tree
	 * @throws InvalidNodeUsageException
	 */
	private ListNode constructTree(Stack<INode> aNodeStack) throws InvalidNodeUsageException
	{
		Stack<INode> inputStack = new Stack<INode>();
		
		while (!aNodeStack.isEmpty()) {
			INode node = aNodeStack.pop();
			
			updateList(node, aNodeStack, inputStack);
		}
		ListNode rootNode = connectSubtrees(inputStack);

		return rootNode;
	}
	/**
	 * A method used to finalize the construction of the tree
	 * @param aInputStack: a stack of the child nodes to be added to the AST root
	 * @return a node whose children are the nodes of the input stack
	 */
	private ListNode connectSubtrees(Stack<INode> aInputStack)
	{
		ListNode root = new ListNode();
		
		List<INode> children = new ArrayList<INode>();
		while(!aInputStack.isEmpty()) {
			children.add(aInputStack.pop());
		}
		root.setChildren(children);

		return root;
	}
	
	/**
	 * A gateway method to update the construction stacks based on the class of aNode
	 * @param aNode: the node to construct
	 * @param aOriginalNodeStack: the nodes left to be constructed
	 * @param aCurrentInputStack: the inputs stack for the next inner node
	 * @throws InvalidNodeUsageException
	 */
	private void updateList(INode aNode, Stack<INode> aOriginalNodeStack, Stack<INode> aCurrentInputStack) throws InvalidNodeUsageException
	{
		if (aNode instanceof AbstractCommandNode) {
			populateCommandNode((AbstractCommandNode) aNode, aOriginalNodeStack, aCurrentInputStack);
		} else if (aNode instanceof ListStartNode) {
			populateListNode((ListStartNode) aNode, aOriginalNodeStack, aCurrentInputStack);
		} else {
			aCurrentInputStack.push(aNode);
		}
	}
	/**
	 * A method used to construct the subtree of a command node
	 * @param aNode
	 * @param aOriginalNodeStack
	 * @param aCurrentInputStack
	 * @throws InvalidNodeUsageException
	 */
	private void populateCommandNode(AbstractCommandNode aNode, Stack<INode> aOriginalNodeStack, Stack<INode> aCurrentInputStack) throws InvalidNodeUsageException
	{
		ArrayList<INode> inputList = new ArrayList<INode>();
		for (int i=0; i<aNode.getNumberOfInputs(); ++i) {
			INode inputNode = aCurrentInputStack.pop();
			inputList.add(inputNode);
		}
		aNode.setChildren(inputList);
		aCurrentInputStack.push(aNode);
	}
	/**
	 * A method used to construct the subtree of a list node 
	 * @param aNode
	 * @param aOriginalNodeStack
	 * @param aCurrentInputStack
	 */
	private void populateListNode(ListStartNode aNode, Stack<INode> aOriginalNodeStack, Stack<INode> aCurrentInputStack)
	{
		ListNode listNode = new ListNode();
		
		ArrayList<INode> inputList = new ArrayList<INode>();
		
		INode inputNode = aCurrentInputStack.pop();
		while (!(inputNode instanceof ListEndNode)) {
			inputList.add(inputNode);
			inputNode = aCurrentInputStack.pop();
		}
		listNode.setChildren(inputList);
		aCurrentInputStack.push(listNode);
	}
}
