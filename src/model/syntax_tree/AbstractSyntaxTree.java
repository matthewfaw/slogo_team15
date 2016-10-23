package model.syntax_tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import model.node.BeginBraceNode;
import model.node.BranchNode;
import model.node.CommandNode;
import model.node.EndBraceNode;
import model.node.Node;
import model.node.ListNode;
import model.node.NodeState;
import model.node.NullNode;

public class AbstractSyntaxTree {
	private Node myRoot;
	
	//XXX: These should be moved to another class. Visitor pattern might work here?
	private Stack<CommandNode> myExpressionStack;
//	private Stack<INode> myInputStack;
	
	public AbstractSyntaxTree(Stack<Node> aNodeStack)
	{
		myExpressionStack = new Stack<CommandNode>();
		
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
	private void updateList(CommandNode aNode, Stack<Node> aOriginalNodeStack, Stack<Node> aCurrentInputStack)
	{
		for (int i=0; i<aNode.getNumberOfInputs(); ++i) {
			Node inputNode = aCurrentInputStack.pop();
			aNode.addChild(inputNode);
			aCurrentInputStack.push(inputNode);
		}
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
	private void updateList(Node aNode, Stack<Node> aOriginalNodeStack, Stack<Node> aCurrentInputStack)
	{
		aCurrentInputStack.push(aOriginalNodeStack.pop());
	}

	public void executeNextInstruction()
	{
		Node currentTopLevelNode = getNextUnvisitedChild(myRoot);
		buildCallStackForNextInstruction(currentTopLevelNode);

		CommandNode nextInstruction = myExpressionStack.peek();
		if (nextInstruction == null) {
			// Do nothing
			return;
		}
		
		if (allInputsAreReadyToBeUsed(nextInstruction)) {
			performEvaluation(nextInstruction);
		}
	}
	private void performEvaluation(CommandNode aNextInstruction)
	{
		// Call the next instruction
		aNextInstruction.eval(aNextInstruction.getChildren());
		// Mark nodes as visited
		aNextInstruction.setState(NodeState.VISITED);
		// Update the stack
		myExpressionStack.pop();
	}
	private void performEvaluation(BranchNode aCondition)
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
	private boolean allInputsAreReadyToBeUsed(CommandNode aParentNode)
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
	private Node getNextUnvisitedChild(BranchNode aParentNode)
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