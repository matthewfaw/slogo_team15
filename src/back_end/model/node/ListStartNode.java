package back_end.model.node;

import back_end.model.command.ICommand;
import back_end.model.exception.ArgumentException;


public class ListStartNode extends Node {

    public ListStartNode (ICommand aCommand, int aNumberOfInputs, String aUserInput) {
        super(aCommand, aNumberOfInputs, aUserInput);
    }

    @Override
    public double eval () throws ArgumentException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getName () {
        return null;
    }

    @Override
    public double getValue () {
        return 0;
    }

}
