package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.command.ICommand;
import back_end.model.exception.InvalidInputNumberException;
import back_end.model.exception.InvalidNodeUsageException;


public abstract class ICommandBranch implements ICommand {
	
    public double eval (IReadableInput ... aList) throws InvalidNodeUsageException {
        return aList[aList.length - 1].getValue();
    }
    
    protected abstract int evalConditionInNode (IReadableInput...aList) throws InvalidInputNumberException, InvalidNodeUsageException;

    public int evalCondition (IReadableInput ... aList) throws InvalidNodeUsageException, InvalidInputNumberException {
    	try {
    		return evalConditionInNode(aList); 
    	} catch (ArrayIndexOutOfBoundsException e) {
    		e.addSuppressed(new InvalidInputNumberException("There are too few inputs"));
    	}
    	return 0;
    }
    
    protected void errorCheckForTooManyInputs(int aListLength, int aNumber) throws InvalidInputNumberException {
    	if (aListLength >= aNumber) throw new InvalidInputNumberException("There are too many inputs");
    }
}
