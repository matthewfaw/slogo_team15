package back_end.model.node.command_nodes;

import back_end.model.command.ICommand;
import back_end.model.exception.ArgumentException;
import back_end.model.node.Node;
import back_end.model.states.ScopeController;


public class CommandNode extends Node {

    private ICommand myCommand;
    private int myNumberOfInputs;
    private double myOutput;

    public CommandNode (ICommand aCommand, int aNumberOfInputs, String aUserInput, ScopeController aScopeController) {
        super(aCommand, aNumberOfInputs, aUserInput, aScopeController);

        myCommand = aCommand;
        myNumberOfInputs = aNumberOfInputs;
    }

    @Override
    public double eval () throws ArgumentException {
        if (super.getChildren().size() != myNumberOfInputs) {
            throw new ArgumentException("Invalid number of arguments");
        }
        Node[] inputList = super.getChildren().toArray(new Node[super.getChildren().size()]);
        myOutput = myCommand.eval(inputList);
        return myOutput;
    }

    public int getNumberOfInputs () {
        return myNumberOfInputs;
    }

    @Override
    public String getName () {
        return null;
    }

    @Override
    public double getValue () {
        return myOutput;
    }

}
