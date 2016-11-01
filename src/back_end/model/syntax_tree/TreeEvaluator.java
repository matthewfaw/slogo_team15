package back_end.model.syntax_tree;
import back_end.model.exception.ArgumentException;
import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.INode;
import back_end.model.node.NodeState;
import back_end.model.node.dummy_nodes.ListEndNode;
import back_end.model.node.dummy_nodes.ListStartNode;
import back_end.model.node.dummy_nodes.NullNode;
import back_end.model.node.inner_nodes.AbstractInnerNode;
import back_end.model.node.inner_nodes.command_nodes.AbstractCommandNode;
import back_end.model.node.inner_nodes.command_nodes.branching_nodes.AbstractBranchNode;
import back_end.model.node.inner_nodes.command_nodes.input_nodes.AbstractInputCommandNode;
import back_end.model.node.inner_nodes.command_nodes.input_nodes.CommandDefinitionNode;
import back_end.model.node.inner_nodes.list_nodes.ListNode;
import back_end.model.node.leaf_nodes.AbstractLeafNode;
import back_end.model.node.leaf_nodes.VariableNode;

import java.util.List;
import java.util.Stack;

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
	public void executeNextInstruction() throws ArgumentException, InvalidNodeUsageException
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
	private void performEvaluation(AbstractInnerNode aNode) throws ArgumentException, InvalidNodeUsageException
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
