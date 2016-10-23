package models.syntax_tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.sun.corba.se.impl.orbutil.graph.Node;

import models.syntax_tree.node.NodeState;

public class AbstractSyntaxTree {
	private Node myRoot;
	
	//XXX: These should be moved to another class. Visitor pattern might work here?
	private Stack<Node> myExpressionStack;
//	private Stack<Node> myInputStack;
	
	public AbstractSyntaxTree(Stack<Node> aNodeStack)
	{
		// Build the tree
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
	private void updateList(MethodNode aNode, Stack<Node> aOriginalNodeStack, Stack<Node> aCurrentInputStack)
	{
		for (int i=0; i<aNode.getNumberOfInputArguments(); ++i) {
			Node inputNode = aCurrentInputStack.pop();
			aNode.addChild(inputNode);
			aCurrentInputStack.push(inputNode);
		}
	}
	private void updateList(LeftBraceNode aNode, Stack<Node> aOriginalNodeStack, Stack<Node> aCurrentInputStack)
	{
		ListNode listNode = new ListNode();
		Node inputNode = aCurrentInputStack.pop();
		while (!(inputNode instanceof RightBraceNode)) {
			listNode.addChild(inputNode);
			inputNode = aCurrentInputStack.pop();
		}
		aCurrentInputStack.pop();
		aCurrentInputStack.push(listNode);
	}
	private void updateList(Node aNode, Stack<Node> aOriginalNodeStack, Stack<Node> aCurrentInputStack)
	{
		aCurrentInputStack.push(aOriginalNodeStack.pop());
	}

	public void executeNextInstruction()
	{
		Node currentTopLevelNode = getNextUnvisitedChild(myRoot);
		buildCallStackForNextInstruction(currentTopLevelNode);

		MethodNode nextInstruction = myExpressionStack.peek();
		if (nextInstruction == null) {
			// Do nothing
			return;
		}
		
		if (allInputsAreReadyToBeUsed(nextInstruction)) {
			performEvaluation(nextInstruction);
		}
	}
	private void performEvaluation(MethodNode aNextInstruction)
	{
		// Call the next instruction
		aNextInstruction.evaluate(aNextInstruction.getChildren());
		// Mark nodes as visited
		aNextInstruction.setState(NodeState.VISITED);
		// Update the stack
		myExpressionStack.pop();
	}
	private void performEvaluation(BranchingNode aCondition)
	{
		if (aCondition.getEvaluationState() == NodeState.EVALUATING_CONDITION) {
			aCondition.evaluateCondition(aCondition.getChildConditions());
			if (aCondition.getConditionEvaluation()) {
				myExpressionStack.pop();
			}
		} else if (aCondition.getEvaluationState() == NodeState.EVALUATING_BRANCH) {
			aCondition.evaluate(aCondition.getCurrentBranch().getChildren());
		}
	}
	private boolean allInputsAreReadyToBeUsed(MethodNode aParentNode)
	{
		return allInputsAreReadyToBeUsed(aParentNode.getChildren());
	}
	private boolean allInputsAreReadyToBeUsed(BranchNode aParentNode)
	{
		if (aParentNode.getEvaluationState() == NodeState.EVALUATING_CONDITION) {
			return allInputsAreReadyToBeUsed(aParentNode.getConditionChildren());
		} else if (aParentNode.getEvaluationState() == NodeState.EVALUATING_BRANCH) {
			return allInputsAreReadyToBeUsed(aParentNode.getActiveBranch().getChildren());
		}
	}
	private boolean allInputsAreReadyToBeUsed(List<Node> aNodes)
	{
		for (Node child: aNodes) {
			if (child.getState() != NodeState.VISITED) {
				return false;
			}
		}
		return true;
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
	private void buildCallStackForNextInstruction(MethodNode aNode)
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
	private Node getNextUnvisitedChild(BranchingNode aParentNode)
	{
		if (aParentNode.getEvaluationState() == NodeState.EVALUATING_CONDITION) {
			return getNextUnvisitedChild(aParentNode.getChildConditions());
		} else if (aParentNode.getEvaluationState() == NodeState.EVALUATING_BRANCH) {
			return getNextUnvisitedChild(aParentNode.getActiveBranch().getChildren());
		}
	}
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