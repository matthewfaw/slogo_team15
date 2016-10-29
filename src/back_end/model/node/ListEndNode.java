package back_end.model.node;

import back_end.model.command.ICommand;
import back_end.model.exception.ArgumentException;


public class ListEndNode extends Node {

    public ListEndNode (ICommand aCommand, int aNumberOfInputs, String aUserInput) {
        super(aCommand, aNumberOfInputs, aUserInput);
    }

    @Override
    public double eval () throws ArgumentException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getName () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public double getValue () {
        // TODO Auto-generated method stub
        return 0;
    }

}
