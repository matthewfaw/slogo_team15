package back_end.model.command;

import back_end.model.node.IReadableInput;


public class ArcTangentCommand implements ICommand {

    public ArcTangentCommand () {
    }

    @Override
    public double eval (IReadableInput ... aList) {
        return Math.atan(aList[0].getValue());
    }

}
