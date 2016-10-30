package back_end.model.node.leaf_nodes;

import back_end.model.command.ICommand;
import back_end.model.exception.ArgumentException;
import back_end.model.states.ScopeController;


public class ConstantNode implements ILeafNode {

    private double myValue;

    public ConstantNode (ICommand aCommand, int aNumberOfInputs, String aUserInput, ScopeController aScopeController) {
        myValue = Double.parseDouble(aUserInput);
    }

//    @Override
//    public double eval () throws ArgumentException {
//        // TODO Auto-generated method stub
//        return myValue;
//    }
//
//    @Override
//    public String getName () {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public double getValue () {
//        return myValue;
//    }

}
