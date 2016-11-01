package back_end.model.syntax_tree;

import java.util.Stack;

import back_end.model.exception.ArgumentException;
import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.INode;
import back_end.model.node.dummy_nodes.NullNode;
import back_end.model.node.inner_nodes.AbstractInnerNode;
import back_end.model.node.leaf_nodes.AbstractLeafNode;

class InstructionCallStackManager {
	private Stack<AbstractInnerNode> myExpressionStack;
	private AvailableNodeFinder myNodeFinder;

	InstructionCallStackManager()
	{
		myExpressionStack = new Stack<AbstractInnerNode>();

		myNodeFinder = new AvailableNodeFinder();
	}
	
	void buildCallStackForNextInstruction(INode aNode) throws InvalidNodeUsageException, ArgumentException
	{
		if (aNode instanceof AbstractLeafNode) {
			buildCallStackForNextInstruction((AbstractLeafNode) aNode);
		} else if (aNode instanceof AbstractInnerNode) {
			buildCallStackForNextInstruction((AbstractInnerNode) aNode);
		} else if (aNode instanceof NullNode) {
			buildCallStackForNextInstruction((NullNode) aNode);
		}
	}
	
	AbstractInnerNode getNextInstruction()
	{
		return myExpressionStack.peek();
	}
	AbstractInnerNode removeNextInstruction()
	{
		return myExpressionStack.pop();
	}
	
	private void buildCallStackForNextInstruction(AbstractLeafNode aNode) throws InvalidNodeUsageException, ArgumentException
	{
		if (!aNode.isVisited()) {
			aNode.eval();
		}
	}
	private void buildCallStackForNextInstruction(AbstractInnerNode aNode) throws InvalidNodeUsageException, ArgumentException
	{
		if (!aNode.isVisited()) {
			if (!myExpressionStack.contains(aNode)) {
				myExpressionStack.push(aNode);
			}
			buildCallStackForNextInstruction(myNodeFinder.getNextUnvisitedChild(aNode));
		}
	}
	private void buildCallStackForNextInstruction(NullNode aNode)
	{
		// Do nothing
	}
}
