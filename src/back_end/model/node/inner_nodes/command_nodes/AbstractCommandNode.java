package back_end.model.node.inner_nodes.command_nodes;

import back_end.model.command.ICommand;
import back_end.model.exception.ArgumentException;
import back_end.model.node.INode;
import back_end.model.node.inner_nodes.IInnerNode;
import back_end.model.states.ScopeController;


public abstract class AbstractCommandNode implements IInnerNode {

//    private ICommand myCommand;
    private int myNumberOfInputs;
//    private double myOutput;

    protected AbstractCommandNode (ICommand aCommand, int aNumberOfInputs, String aUserInput, ScopeController aScopeController) {
//        myCommand = aCommand;
        myNumberOfInputs = aNumberOfInputs;
    }
//
//    @Override
//    public double eval () throws ArgumentException {
//        if (super.getChildren().size() != myNumberOfInputs) {
//            throw new ArgumentException("Invalid number of arguments");
//        }
//        INode[] inputList = super.getChildren().toArray(new INode[super.getChildren().size()]);
//        myOutput = myCommand.eval(inputList);
//        return myOutput;
//    }

    public int getNumberOfInputs () {
        return myNumberOfInputs;
    }

//    @Override
//    public String getName () {
//        return null;
//    }
//
//    @Override
//    public double getValue () {
//        return myOutput;
//    }

}
