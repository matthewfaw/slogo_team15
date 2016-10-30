package back_end.model.node.dummy_nodes;

import back_end.model.command.ICommand;
import back_end.model.exception.ArgumentException;
import back_end.model.node.INode;
import back_end.model.states.ScopeController;


public class ListEndNode extends AbstractDummyNode {

	protected ListEndNode(ICommand aCommand, int aNumberOfInputs, String aUserInput, ScopeController aScopeController) {
		super(aCommand, aNumberOfInputs, aUserInput, aScopeController);
		// TODO Auto-generated constructor stub
	}

}
