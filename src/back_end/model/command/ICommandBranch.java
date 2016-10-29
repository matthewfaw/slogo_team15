package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.command.ICommand;


public abstract class ICommandBranch implements ICommand {
	
    public double eval (IReadableInput ... aList) {
        return aList[aList.length - 1].getValue();
    }

    public abstract int evalCondition (IReadableInput ... aList);
}
