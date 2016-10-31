package back_end.model.syntax_tree;
import back_end.model.exception.ArgumentException;
import back_end.model.node.INode;
import back_end.model.node.NodeState;
import back_end.model.node.dummy_nodes.ListEndNode;
import back_end.model.node.dummy_nodes.ListStartNode;
import back_end.model.node.dummy_nodes.NullNode;
import back_end.model.node.inner_nodes.command_nodes.AbstractCommandNode;
import back_end.model.node.inner_nodes.command_nodes.CustomNode;
import back_end.model.node.inner_nodes.command_nodes.branching_nodes.AbstractBranchNode;
import back_end.model.node.inner_nodes.command_nodes.input_nodes.CommandDefinitionNode;
import back_end.model.node.inner_nodes.list_nodes.ListNode;
import back_end.model.node.leaf_nodes.AbstractLeafNode;
import back_end.model.node.leaf_nodes.VariableNode;

import java.util.List;
import java.util.Stack;

public class TreeEvaluator {
	private AbstractSyntaxTree myAST;
	private Stack<INode> myExpressionStack;
	
	public TreeEvaluator(AbstractSyntaxTree aTree)
	{
		myAST = aTree;
		myExpressionStack = new Stack<INode>();
	}
	public boolean hasNextInstruction()
	{
		return myRoot.getState() == NodeState.AVAILABLE; 
	}
	public void executeNextInstruction() throws ArgumentException
	{
		INode currentTopLevelNode = getNextUnvisitedChild(myRoot);
		buildCallStackForNextInstruction(currentTopLevelNode);
		INode nextInstruction = myExpressionStack.peek();
		if (nextInstruction == null) {
			// Do nothing
			return;
		}
		
		if (allInputsAreReadyToBeUsed(nextInstruction)) {
			performEvaluation(nextInstruction);
		}
		if (allInstructionsHaveBeenEvaluated()) {
			myRoot.setState(NodeState.VISITED);
		}
	}
	private boolean allInstructionsHaveBeenEvaluated()
	{
		for (INode child: myRoot.getChildren()) {
			if (child.getState() != NodeState.VISITED) {
				return false;
			}
		}
		return true;
	}


	private void performEvaluation(INode aNode) throws ArgumentException
	{
		if (aNode instanceof AbstractCommandNode) {
			performEvaluation((AbstractCommandNode) aNode);
		} else if (aNode instanceof AbstractBranchNode) {
			performEvaluation((AbstractBranchNode) aNode);
		} else if (aNode instanceof CommandDefinitionNode) {
			performEvaluation((CommandDefinitionNode) aNode); 
		} else if (aNode instanceof CustomNode) {
			performEvaluation((CustomNode) aNode);
		} else {
			//XXX: add error here
		}
	}
	private void performEvaluation(AbstractCommandNode aNextInstruction) throws ArgumentException
	{
		// Call the next instruction
		aNextInstruction.eval();
		// Mark nodes as visited
		aNextInstruction.setState(NodeState.VISITED);
		// Update the stack
		myExpressionStack.pop();
	}
	private void performEvaluation(AbstractBranchNode aCondition) throws ArgumentException
	{
		if (aCondition.getEvaluationState() == NodeState.EVALUATING_INPUTS) {
			aCondition.evalCondition();
			if (aCondition.getConditionEvaluation() == -1) {
				aCondition.setState(NodeState.VISITED);
				myExpressionStack.pop();
			} else {
				aCondition.setEvaluationState(NodeState.EVALUATING_BRANCH);
			}
		} else if (aCondition.getEvaluationState() == NodeState.EVALUATING_BRANCH) {
			aCondition.eval();
			aCondition.setEvaluationState(NodeState.EVALUATING_INPUTS);
			//unmark all visited children
			aCondition.unmarkAllChildren();
		}
	}
	//XXX: change, since same as CommandNode
	private void performEvaluation(CommandDefinitionNode aNode) throws ArgumentException
	{
		aNode.eval();
		aNode.setState(NodeState.VISITED);
		myExpressionStack.pop();
	}
	//XXX: change, since similar to BranchNode
	private void performEvaluation(CustomNode aNode) throws ArgumentException
	{
		if (aNode.getEvaluationState() == NodeState.EVALUATING_INPUTS) {
			aNode.evalInputs();
			aNode.setEvaluationState(NodeState.EVALUATING_BRANCH);
		} else if (aNode.getEvaluationState() == NodeState.EVALUATING_BRANCH) {
			aNode.eval();
			aNode.unmarkCustomChildren();
			aNode.setState(NodeState.VISITED);
		}
	}
	private boolean allInputsAreReadyToBeUsed(INode aParentNode)
	{
		//XXX: Note that for some cases--ToNode, for instance--this works by chance. we should probably come up with
		// a more robust solution
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
	private boolean allInputsAreReadyToBeUsed(List<INode> aNodes)
	{
		for (INode child: aNodes) {
			if (child.getState() != NodeState.VISITED) {
				return false;
			}
		}
		return true;
	}
	
	//XXX: dispatcher for multiple dispatch method.
	// is there a nice way around this???
	private void buildCallStackForNextInstruction(INode aNode)
	{
		if (aNode instanceof NullNode) {
			buildCallStackForNextInstruction((NullNode) aNode);
		} else if (aNode instanceof AbstractLeafNode) {
			buildCallStackForNextInstruction((AbstractLeafNode) aNode);
		} else if (aNode instanceof AbstractCommandNode) {
			buildCallStackForNextInstruction((AbstractCommandNode) aNode);
		} else if (aNode instanceof AbstractBranchNode) {
			buildCallStackForNextInstruction((AbstractBranchNode) aNode);
		} else if (aNode instanceof CommandDefinitionNode) {
			buildCallStackForNextInstruction((CommandDefinitionNode) aNode);
		} else if (aNode instanceof CustomNode) {
			buildCallStackForNextInstruction((CustomNode) aNode);
		} else if (aNode instanceof ListNode) {
			buildCallStackForNextInstruction((ListNode) aNode);
		}
	}
	private void buildCallStackForNextInstruction(NullNode aNode)
	{
		// Do nothing
	}
	private void buildCallStackForNextInstruction(AbstractLeafNode aNode)
	{
		if (isAvailableForTraversal(aNode)) {
//			myInputStack.push(aNode);
			aNode.setState(NodeState.VISITED);
		}
	}
	private void buildCallStackForNextInstruction(AbstractCommandNode aNode)
	{
		if (isAvailableForTraversal(aNode)) {
			if (!myExpressionStack.contains(aNode)) {
				myExpressionStack.push(aNode);
			}
			buildCallStackForNextInstruction(getNextUnvisitedChild(aNode));
		}
	}
	//XXX: Remove this method--same as the method for CommandNode
	private void buildCallStackForNextInstruction(AbstractBranchNode aNode)
	{
		if (isAvailableForTraversal(aNode)) {
			if (!myExpressionStack.contains(aNode)) {
				myExpressionStack.push(aNode);
			}
			buildCallStackForNextInstruction(getNextUnvisitedChild(aNode));
		}
	}
	//XXX: Refactor this method to make it cleaner to read
	private void buildCallStackForNextInstruction(ListNode aNode)
	{
		if (isAvailableForTraversal(aNode)) {
			if (allChildrenAreVisited(aNode)) {
				aNode.setState(NodeState.VISITED);
			} else {
				buildCallStackForNextInstruction(getNextUnvisitedChild(aNode));
			}
		}
	}
	//XXX: add heirarchy to remove repeated code
	private void buildCallStackForNextInstruction(CommandDefinitionNode aNode)
	{
		if (isAvailableForTraversal(aNode)) {
			if (!myExpressionStack.contains(aNode)) {
				myExpressionStack.push(aNode);
			}
			buildCallStackForNextInstruction(getNextUnvisitedChild(aNode));
		}
	}
	//XXX: add heirarchy to remove repeated code
	private void buildCallStackForNextInstruction(CustomNode aNode)
	{
		if (isAvailableForTraversal(aNode)) {
			if (!myExpressionStack.contains(aNode)) {
				myExpressionStack.push(aNode);
			}
			buildCallStackForNextInstruction(getNextUnvisitedChild(aNode));
		}
	}
	
	private boolean allChildrenAreVisited(INode aParentNode)
	{
		for (INode child: aParentNode.getChildren()) {
			if (child.getState() != NodeState.VISITED) {
				return false;
			}
		}
		return true;
	}

	private INode getNextUnvisitedChild(INode aParentNode)
	{
		return getNextUnvisitedChild(aParentNode.getChildren());
	}
	private INode getNextUnvisitedChild(List<INode> aNodes) 
	{
		for (INode child: aNodes) {
			if (isAvailableForTraversal(child)) {
				return child;
			}
		}
		
		return new NullNode();
	}
	private boolean isAvailableForTraversal(INode aNode)
	{
		NodeState state = aNode.getState();
		return state == NodeState.AVAILABLE;
	}
}
