package back_end.model.syntax_tree;

public class TreeEvaluator {
	private AbstractSyntaxTree myRoot;
	private Stack<Node> myExpressionStack;

	public TreeEvaluator(AbstractSyntaxTree aTree)
	{
		myRoot = aTree.getRoot();
		myExpressionStack = new Stack<Node>();
	}
	
	public boolean hasNextInstruction()
	{
		return myRoot.getState() == NodeState.AVAILABLE; 
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
		if (allInstructionsHaveBeenEvaluated()) {
			myRoot.setState(NodeState.VISITED);
		}
	}
	
	private boolean allInstructionsHaveBeenEvaluated()
	{
		for (Node child: myRoot.getChildren()) {
			if (child.getState() != NodeState.VISITED) {
				return false;
			}
		}
		return true;
	}
	
	private void performEvaluation(Node aNode) throws ArgumentException
	{
		if (aNode instanceof CommandNode) {
			performEvaluation((CommandNode) aNode);
		} else if (aNode instanceof BranchNode) {
			performEvaluation((BranchNode) aNode);
		} else if (aNode instanceof ToNode) {
			performEvaluation((ToNode) aNode); 
		} else if (aNode instanceof CustomNode) {
			performEvaluation((CustomNode) aNode);
		} else {
			//XXX: add error here
		}
	}
	private void performEvaluation(CommandNode aNextInstruction) throws ArgumentException
	{
		// Call the next instruction
		aNextInstruction.eval();
		// Mark nodes as visited
		aNextInstruction.setState(NodeState.VISITED);
		// Update the stack
		myExpressionStack.pop();
	}
	private void performEvaluation(BranchNode aCondition) throws ArgumentException
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
	private void performEvaluation(ToNode aNode) throws ArgumentException
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
	private boolean allInputsAreReadyToBeUsed(Node aParentNode)
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
		} else if (aNode instanceof BranchNode) {
			buildCallStackForNextInstruction((BranchNode) aNode);
		} else if (aNode instanceof ToNode) {
			buildCallStackForNextInstruction((ToNode) aNode);
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
			if (!myExpressionStack.contains(aNode)) {
				myExpressionStack.push(aNode);
			}
			buildCallStackForNextInstruction(getNextUnvisitedChild(aNode));
		}
	}
	//XXX: Remove this method--same as the method for CommandNode
	private void buildCallStackForNextInstruction(BranchNode aNode)
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
	private void buildCallStackForNextInstruction(ToNode aNode)
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
	
	private boolean allChildrenAreVisited(Node aParentNode)
	{
		for (Node child: aParentNode.getChildren()) {
			if (child.getState() != NodeState.VISITED) {
				return false;
			}
		}
		return true;
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
