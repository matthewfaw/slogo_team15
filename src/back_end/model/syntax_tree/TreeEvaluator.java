// This entire file is part of my masterpiece.
// Matthew Faw

package back_end.model.syntax_tree;

import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.exception.InvalidInputNumberException;
import back_end.model.node.INode;
import back_end.model.node.NodeState;
import back_end.model.node.inner_nodes.AbstractInnerNode;
import back_end.model.node.inner_nodes.command_nodes.AbstractCommandNode;

/**
 * The purpose of this class is to evaluate the instructions coded into the AST
 * This class is an interpretation of the visitor design pattern, with the purpose
 * of separating the evaluation of the tree from the construction and structure of
 * the tree.  By separating the construction and evaluation of the AST, the code becomes
 * far more modular and maintainable.  Additionally, the tree is not restricted to being 
 * evaluated in a specific way.  This makes adding new features quite easy, and makes the
 * AST closed, but open to different forms of traversal and evaluation
 * 
 * Note that during this traversal, the complexity of considering which inputs to choose
 * when evaluating a given node is completely abstracted away from the evaluator, since
 * this is the responsibility of the Node classes.  Additionally, the evaluator does not
 * need to know about the environment variables, since those are abstracted away by the 
 * Command classes
 * 
 * This class assumes that it is passed a non-null AST
 * This class depends on The AbstractSyntaxTree class to construct a tree which this
 * class can evaluate. It also depends on the Node classes to for instruction evaluation
 * 
 * Given an AbstractSyntaxTree ast, one may use this class as follows:
 * TreeEvaluator evaluator = new TreeEvaluator(ast);
 * To check if the tree has another instruction:
 * ast.hasNextInstruction();
 * To execute the next instruction, 
 * ast.executeNextInstruction();
 * 
 * @author matthewfaw
 *
 */
public class TreeEvaluator {
	private AbstractSyntaxTree myAST;
	
	private AvailableNodeFinder myNodeFinder;
	private InstructionCallStackManager myCallStackManager;
	
	/**
	 * A method that constructs the tree evaluator, given an
	 * abstract syntax tree
	 * assumes that aTree is not null
	 * @param aTree
	 */
	public TreeEvaluator(AbstractSyntaxTree aTree)
	{
		myAST = aTree;
		
		myNodeFinder = new AvailableNodeFinder();
		myCallStackManager = new InstructionCallStackManager();
	}
	/**
	 * Determines whether the AST has been fully evaluated or not
	 * @return true if fully evaluated, false otherwise
	 * @throws InvalidNodeUsageException
	 */
	public boolean hasNextInstruction() throws InvalidNodeUsageException
	{
		return myAST.getRoot().getState() == NodeState.AVAILABLE; 
	}
	/**
	 * An atomic method to execute the next unexecuted instruction in the tree
	 * If the tree is invalidly constructed, then an error will be thrown, alerting the user
	 * that invalid number of inputs have been provided to a specific method
	 * 
	 * @throws InvalidInputNumberException
	 * @throws InvalidNodeUsageException
	 */
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
	/**
	 * Method used to evaluate an evaluatable node
	 * This method will throw an error if the node to be evaluated cannot be evaluated, or has improper number of inputs
	 * @param aNode
	 * @throws InvalidInputNumberException
	 * @throws InvalidNodeUsageException
	 */
	private void performEvaluation(AbstractInnerNode aNode) throws InvalidInputNumberException, InvalidNodeUsageException
	{
		aNode.eval();
		myCallStackManager.removeNextInstruction();
	}
	/**
	 * A method to determine if all children of the root node have been evaluated
	 * Assumes that the AST is not null
	 * @return true if all instructions have been evaluated, false otherwise
	 * @throws InvalidNodeUsageException
	 */
	private boolean allInstructionsHaveBeenEvaluated() throws InvalidNodeUsageException
	{
		return myAST.getRoot().allChildrenAreEvaluated();
	}
	/**
	 * A method to simplify marking the AST as completed.
	 * This method assumes that the AST is not null
	 * @throws InvalidNodeUsageException
	 */
	private void markTreeAsCompleted() throws InvalidNodeUsageException
	{
		myAST.getRoot().setState(NodeState.VISITED);
	}
	
}
