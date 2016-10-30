package back_end.model.command;

import back_end.model.node.IReadableInput;


public class TangentCommand implements ICommand {

    public TangentCommand () {
    }

    @Override
    public double eval (IReadableInput ... aList) {
        return Math.tan(aList[0].getValue());
    }

}
