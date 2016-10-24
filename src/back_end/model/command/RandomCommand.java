package back_end.model.command;

import back_end.model.node.IReadableInput;


public class RandomCommand implements ICommand {

    public RandomCommand () {
    }

    @Override
    public double eval (IReadableInput ... aList) {
        return Math.random() * aList[0].getValue();
    }

}
