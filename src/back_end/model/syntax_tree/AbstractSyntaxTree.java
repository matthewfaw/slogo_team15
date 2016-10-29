package back_end.model.syntax_tree;

import java.util.List;
import java.util.Stack;
import back_end.model.exception.ArgumentException;
import back_end.model.node.AssignmentNode;
import back_end.model.node.BeginBraceNode;
import back_end.model.node.BranchNode;
import back_end.model.node.CommandNode;
import back_end.model.node.CustomNode;
import back_end.model.node.EndBraceNode;
import back_end.model.node.ListNode;
import back_end.model.node.Node;
import back_end.model.node.NodeState;
import back_end.model.node.NullNode;
import back_end.model.node.ToNode;
import back_end.model.node.ValueNode;
import back_end.model.node.VariableNode;


public class AbstractSyntaxTree {
	private Node myRoot;
	
	//XXX: These should be moved to another class. Visitor pattern might work here?
	
	public AbstractSyntaxTree(Stack<Node> aNodeStack)
	{
		myRoot = constructTree(aNodeStack);
	}
	
	public Node getRoot()
	{
		return myRoot;
	}
	
	private Node constructTree(Stack<Node> aNodeStack)
	{
		Stack<Node> inputStack = new Stack<Node>();
		
		while (!aNodeStack.isEmpty()) {
			Node node = aNodeStack.pop();
			
			updateList(node, aNodeStack, inputStack);
		}
		Node rootNode = connectSubtrees(inputStack);

		return rootNode;
	}
	private Node connectSubtrees(Stack<Node> aInputStack)
	{
		ListNode root = new ListNode();
		
		while(!aInputStack.isEmpty()) {
			root.addChild(aInputStack.pop());
		}

		return root;
	}
	private void updateList(Node aNode, Stack<Node> aOriginalNodeStack, Stack<Node> aCurrentInputStack)
	{
		if (aNode instanceof CommandNode) {
			updateList((CommandNode) aNode, aOriginalNodeStack, aCurrentInputStack);
		} else if (aNode instanceof ToNode) {
			updateList((ToNode) aNode, aOriginalNodeStack, aCurrentInputStack);
		} else if (aNode instanceof CustomNode) {
			updateList((CustomNode) aNode, aOriginalNodeStack, aCurrentInputStack);
		} else if (aNode instanceof BranchNode) {
			updateList((BranchNode) aNode, aOriginalNodeStack, aCurrentInputStack);
		} else if (aNode instanceof BeginBraceNode) {
			updateList((BeginBraceNode) aNode, aOriginalNodeStack, aCurrentInputStack);
		} else {
			aCurrentInputStack.push(aNode);
		}
	}

	private void updateList(CommandNode aNode, Stack<Node> aOriginalNodeStack, Stack<Node> aCurrentInputStack)
	{
		for (int i=0; i<aNode.getNumberOfInputs(); ++i) {
			Node inputNode = aCurrentInputStack.pop();
			aNode.addChild(inputNode);
		}
		aCurrentInputStack.push(aNode);
	}
	//XXX: This seems kinda hacky, since number of arguments is hard coded, consider changing to be more flexible
	private void updateList(ToNode aNode, Stack<Node> aOriginalNodeStack, Stack<Node> aCurrentInputStack)
	{
		aNode.addNameNode((VariableNode) aCurrentInputStack.pop());
		aNode.addInputVariables((ListNode) aCurrentInputStack.pop());
		aNode.addCustomMethod((ListNode) aCurrentInputStack.pop());

		aCurrentInputStack.push(aNode);
	}
	//XXX: This seems kinda hacky, since number of arguments is hard coded, consider changing to be more flexible
	private void updateList(CustomNode aNode, Stack<Node> aOriginalNodeStack, Stack<Node> aCurrentInputStack)
	{
		aNode.addChildInputs((ListNode) aCurrentInputStack.pop());

		aCurrentInputStack.push(aNode);
	}
	//TODO: add error throwing when the casting fails
	private void updateList(BranchNode aNode, Stack<Node> aOriginalNodeStack, Stack<Node> aCurrentInputStack)
	{
		// Set up conditions
		aNode.addConditions(aCurrentInputStack.pop());
		// Set up branches
		for (int i=1; i<aNode.getNumberOfInputs(); ++i) {
			ListNode inputNode = (ListNode) aCurrentInputStack.pop();
			aNode.addBranchChildren(i - 1, inputNode);
		}
		aCurrentInputStack.push(aNode);
	}
	private void updateList(BeginBraceNode aNode, Stack<Node> aOriginalNodeStack, Stack<Node> aCurrentInputStack)
	{
		ListNode listNode = new ListNode();
		Node inputNode = aCurrentInputStack.pop();
		while (!(inputNode instanceof EndBraceNode)) {
			listNode.addChild(inputNode);
			inputNode = aCurrentInputStack.pop();
		}
//		aCurrentInputStack.pop();
		aCurrentInputStack.push(listNode);
	}
}
