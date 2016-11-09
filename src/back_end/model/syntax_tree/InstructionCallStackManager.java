package back_end.model.syntax_tree;

import java.util.Stack;

import back_end.model.exception.InvalidInputNumberException;
import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.INode;
import back_end.model.node.dummy_nodes.NullNode;
import back_end.model.node.inner_nodes.AbstractInnerNode;
import back_end.model.node.leaf_nodes.AbstractLeafNode;

/**
 * A class that manages the call stack during tree evaluation
 * This class assumes that once the stack is built, the next instruction to 
 * be retrieved is the node at the top of the call stack
 * 
 * To instantiate this class:
 * 
 * InstructionCallStackManager manager = new InstructionCallStackManager();
 * Given a node, we can update the call stack by:
 * manager.buildCallStackForNextInstruction(aNode);
 * To get the next instruction on the stack,
 * manager.getNextInstruction();
 * To remove an instruction from the top of the stack,
 * manager.removeNextInstruction(); 
 * 
 * @author matthewfaw
 *
 */
class InstructionCallStackManager {
	private Stack<AbstractInnerNode> myExpressionStack;
	private AvailableNodeFinder myNodeFinder;

	InstructionCallStackManager()
	{
		myExpressionStack = new Stack<AbstractInnerNode>();

		myNodeFinder = new AvailableNodeFinder();
	}
	
	/**
	 * The primary gateway function for building the call stack.  This method dispatches helper methods for
	 * different subclass types
	 * This class assumes that the three types of nodes that will be input to the method:
	 * AbstractLeafNodes, AbstractInnerNodes, and NullNodes
	 * Since AbstractLeafNodes and AbstractInnerNodes are the two base node classes in the Tree, this assumption
	 * should always be valid
	 * @param aNode
	 * @throws InvalidNodeUsageException
	 * @throws InvalidInputNumberException
	 */
	void buildCallStackForNextInstruction(INode aNode) throws InvalidNodeUsageException, InvalidInputNumberException
	{
		if (aNode instanceof AbstractLeafNode) {
			buildCallStackForNextInstruction((AbstractLeafNode) aNode);
		} else if (aNode instanceof AbstractInnerNode) {
			buildCallStackForNextInstruction((AbstractInnerNode) aNode);
		} else if (aNode instanceof NullNode) {
			buildCallStackForNextInstruction((NullNode) aNode);
		}
	}
	
	/**
	 * A method to get the next instruction on the call stack
	 * Assumes that the expression stack is not empty
	 * 
	 * @return the next node to evaluate
	 */
	AbstractInnerNode getNextInstruction()
	{
		return myExpressionStack.peek();
	}
	/**
	 * Removes the node at the top of the stack
	 * Assumes that the stack is not empty
	 * @return the node that was removed 
	 */
	AbstractInnerNode removeNextInstruction()
	{
		return myExpressionStack.pop();
	}
	
	private void buildCallStackForNextInstruction(AbstractLeafNode aNode) throws InvalidNodeUsageException, InvalidInputNumberException
	{
		if (!aNode.isVisited()) {
			aNode.eval();
		}
	}
	private void buildCallStackForNextInstruction(AbstractInnerNode aNode) throws InvalidNodeUsageException, InvalidInputNumberException
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
