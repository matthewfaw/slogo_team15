package back_end.model.command;

import back_end.model.node.IReadableInput;


public class SineCommand implements ICommand {

    public SineCommand () {
    }

    @Override
    public double eval (IReadableInput ... aList) {
        return Math.sin(aList[0].getValue());
    }

}
