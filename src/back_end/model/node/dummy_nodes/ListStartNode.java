package back_end.model.node.dummy_nodes;

import back_end.model.command.ICommand;
import back_end.model.exception.InvalidInputNumberException;
import back_end.model.node.INode;
import back_end.model.states.ScopeController;


/**
 * A class to represent the opening bracket in the syntax
 * It is assumed that this class is constructed only by the text parser, and is never included in the AST
 * @author matthewfaw
 *
 */
public class ListStartNode extends AbstractDummyNode {

	public ListStartNode(ICommand aCommand, int aNumberOfInputs, String aUserInput,
			ScopeController aScopeController) {
		super();
		// TODO Auto-generated constructor stub
	}

}
