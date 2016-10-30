package back_end.model.node.inner_nodes.command_nodes.input_nodes;

import back_end.model.command.ICommand;
import back_end.model.node.inner_nodes.command_nodes.AbstractCommandNode;
import back_end.model.states.ScopeController;

public class InputCommandNode extends AbstractCommandNode {

	protected InputCommandNode(ICommand aCommand, int aNumberOfInputs, String aUserInput,
			ScopeController aScopeController) {
		super(aCommand, aNumberOfInputs, aUserInput, aScopeController);
		// TODO Auto-generated constructor stub
	}

}
