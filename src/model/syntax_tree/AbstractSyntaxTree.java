package model.syntax_tree;

import java.util.List;
import java.util.Stack;

import model.exception.ArgumentException;
import model.node.BeginBraceNode;
import model.node.BranchNode;
import model.node.CommandNode;
import model.node.EndBraceNode;
import model.node.Node;
import model.node.ListNode;
import model.node.NodeState;
import model.node.NullNode;
import model.node.ValueNode;

public class AbstractSyntaxTree {
	private Node myRoot;
	
	//XXX: These should be moved to another class. Visitor pattern might work here?
	private Stack<Node> myExpressionStack;
//	private Stack<INode> myInputStack;
	
	public AbstractSyntaxTree(Stack<Node> aNodeStack)
	{
		myExpressionStack = new Stack<Node>();
		
		myRoot = constructTree(aNodeStack);
	}
	
	private Node constructTree(Stack<Node> aNodeStack)
	{
		Stack<Node> inputStack = new Stack<Node>();
		
		while (!aNodeStack.isEmpty()) {
			Node node = aNodeStack.pop();
			
			//XXX: Unsure if this will actually modify these stacks properly
			//if this doesn't work, switch back to if/else
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
	private void updateList(BeginBraceNode aNode, Stack<Node> aOriginalNodeStack, Stack<Node> aCurrentInputStack)
	{
		ListNode listNode = new ListNode();
		Node inputNode = aCurrentInputStack.pop();
		while (!(inputNode instanceof EndBraceNode)) {
			listNode.addChild(inputNode);
			inputNode = aCurrentInputStack.pop();
		}
		aCurrentInputStack.pop();
		aCurrentInputStack.push(listNode);
	}
	public void executeNextInstruction() throws ArgumentException
	{
		Node currentTopLevelNode = getNextUnvisitedChild(myRoot);
		buildCallStackForNextInstruction(currentTopLevelNode);

		Node nextInstruction = myExpressionStack.peek();
		if (nextInstruction == null) {
			// Do nothing
			return;
		}
		
		if (allInputsAreReadyToBeUsed(nextInstruction)) {
			performEvaluation(nextInstruction);
		}
	}
	private void performEvaluation(Node aNode) throws ArgumentException
	{
		if (aNode instanceof CommandNode) {
			performEvaluation((CommandNode) aNode);
		} else if (aNode instanceof BranchNode) {
			performEvaluation((BranchNode) aNode);
		} else {
			//XXX: add error here
		}
	}
	private void performEvaluation(CommandNode aNextInstruction) throws ArgumentException
	{
		// Call the next instruction
		aNextInstruction.eval(aNextInstruction.getChildren());
		// Mark nodes as visited
		aNextInstruction.setState(NodeState.VISITED);
		// Update the stack
		myExpressionStack.pop();
	}
	private void performEvaluation(BranchNode aCondition) throws ArgumentException
	{
		if (aCondition.getEvaluationState() == NodeState.EVALUATING_CONDITION) {
			aCondition.evalCondition(aCondition.getChildren());
			if (aCondition.getConditionEvaluation()) {
				myExpressionStack.pop();
			}
		} else if (aCondition.getEvaluationState() == NodeState.EVALUATING_BRANCH) {
			aCondition.eval(aCondition.getChildren());
		}
	}
	private boolean allInputsAreReadyToBeUsed(Node aParentNode)
	{
		return allInputsAreReadyToBeUsed(aParentNode.getChildren());
	}
//	private boolean allInputsAreReadyToBeUsed(BranchNode aParentNode)
//	{
//		if (aParentNode.getEvaluationState() == NodeState.EVALUATING_CONDITION) {
//			return allInputsAreReadyToBeUsed(aParentNode.getConditionChildren());
//		} else if (aParentNode.getEvaluationState() == NodeState.EVALUATING_BRANCH) {
//			return allInputsAreReadyToBeUsed(aParentNode.getActiveBranch().getChildren());
//		}
//	}
	private boolean allInputsAreReadyToBeUsed(List<Node> aNodes)
	{
		for (Node child: aNodes) {
			if (child.getState() != NodeState.VISITED) {
				return false;
			}
		}
		return true;
	}
	
	//XXX: dispatcher for multiple dispatch method.
	// is there a nice way around this???
	private void buildCallStackForNextInstruction(Node aNode)
	{
		if (aNode instanceof NullNode) {
			buildCallStackForNextInstruction((NullNode) aNode);
		} else if (aNode instanceof ValueNode) {
			buildCallStackForNextInstruction((ValueNode) aNode);
		} else if (aNode instanceof CommandNode) {
			buildCallStackForNextInstruction((CommandNode) aNode);
		}
	}
	private void buildCallStackForNextInstruction(NullNode aNode)
	{
		// Do nothing
	}
	private void buildCallStackForNextInstruction(ValueNode aNode)
	{
		if (isAvailableForTraversal(aNode)) {
//			myInputStack.push(aNode);
			aNode.setState(NodeState.VISITED);
		}
	}
	private void buildCallStackForNextInstruction(CommandNode aNode)
	{
		if (isAvailableForTraversal(aNode)) {
			myExpressionStack.push(aNode);
			buildCallStackForNextInstruction(getNextUnvisitedChild(aNode));
		}
	}

	private Node getNextUnvisitedChild(Node aParentNode)
	{
		return getNextUnvisitedChild(aParentNode.getChildren());
	}
//	private Node getNextUnvisitedChild(BranchNode aParentNode)
//	{
//		if (aParentNode.getEvaluationState() == NodeState.EVALUATING_CONDITION) {
//			return getNextUnvisitedChild(aParentNode.getChildConditions());
//		} else if (aParentNode.getEvaluationState() == NodeState.EVALUATING_BRANCH) {
//			return getNextUnvisitedChild(aParentNode.getActiveBranch().getChildren());
//		}
//	}
	private Node getNextUnvisitedChild(List<Node> aNodes) 
	{
		for (Node child: aNodes) {
			if (isAvailableForTraversal(child)) {
				return child;
			}
		}
		
		return new NullNode();
	}
	private boolean isAvailableForTraversal(Node aNode)
	{
		NodeState state = aNode.getState();
		return state == NodeState.AVAILABLE;
	}
}