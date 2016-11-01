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

public class AbstractSyntaxTree {
	private ListNode myRoot;
	
	public AbstractSyntaxTree(Stack<INode> aNodeStack) throws InvalidNodeUsageException
	{
		myRoot = constructTree(aNodeStack);
	}
	ListNode getRoot()
	{
		return myRoot;
	}
	
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
