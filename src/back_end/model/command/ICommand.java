package back_end.model.command;

import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.IReadableInput;


public interface ICommand {

    public double eval (IReadableInput ... aList) throws InvalidNodeUsageException;

}
