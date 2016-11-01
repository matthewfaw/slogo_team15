package back_end.model.syntax_tree;

import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.exception.InvalidInputNumberException;
import back_end.model.node.INode;
import back_end.model.node.NodeState;
import back_end.model.node.inner_nodes.AbstractInnerNode;

public class TreeEvaluator {
	private AbstractSyntaxTree myAST;
	
	private AvailableNodeFinder myNodeFinder;
	private InstructionCallStackManager myCallStackManager;
	
	public TreeEvaluator(AbstractSyntaxTree aTree)
	{
		myAST = aTree;
		
		myNodeFinder = new AvailableNodeFinder();
		myCallStackManager = new InstructionCallStackManager();
	}
	public boolean hasNextInstruction() throws InvalidNodeUsageException
	{
		return myAST.getRoot().getState() == NodeState.AVAILABLE; 
	}
	public void executeNextInstruction() throws InvalidInputNumberException, InvalidNodeUsageException
	{
		INode currentTopLevelNode = myNodeFinder.getNextUnvisitedChild(myAST.getRoot());
		myCallStackManager.buildCallStackForNextInstruction(currentTopLevelNode);
		AbstractInnerNode nextInstruction = myCallStackManager.getNextInstruction();
		if (nextInstruction == null) {
			// Do nothing
			return;
		}
		
		if (nextInstruction.allChildrenAreEvaluated()) {
			performEvaluation(nextInstruction);
		}
		if (allInstructionsHaveBeenEvaluated()) {
			markTreeAsCompleted();
		}
	}
	private void performEvaluation(AbstractInnerNode aNode) throws InvalidInputNumberException, InvalidNodeUsageException
	{
		aNode.eval();
		myCallStackManager.removeNextInstruction();
	}
	private boolean allInstructionsHaveBeenEvaluated() throws InvalidNodeUsageException
	{
		return myAST.getRoot().allChildrenAreEvaluated();
	}
	private void markTreeAsCompleted() throws InvalidNodeUsageException
	{
		myAST.getRoot().setState(NodeState.VISITED);
	}
	
}
