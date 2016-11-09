// This entire file is part of my masterpiece.
// Matthew Faw

package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;

import back_end.model.exception.InvalidNodeUsageException;

/**
 * The purpose of this class is to allow users to assign variables inside of the current scope
 * I think this class is well designed because it allows us to hide away the variables inside
 * of the environment from the TreeEvaluator.  Thus, this method completely encapsulates away
 * the usage of any given node in the evaluation of a given node 
 * 
 * Note that this class assumes it is given a valid number of inputs, since the number of inputs
 * must be valid upon tree construction, else there was an error already thrown
 * 
 * One may use the eval method within the Command node classes in the following way:
 * After constructing the inputs for the command within the node class,
 * myReturnValue = myCommand.eval(listOfInputs);
 *  
 * 
 * Note that Hannah and Matthew created this class together, as it was necessary for us to work
 * together on this issue to figure out how exactly assignment operations should be handled
 * @author matthewfaw
 * @author Hannah Fuchshuber
 * 
 */
public class MakeVariableCommand implements ICommand {

	private static final int VARIABLE_NAME_INDEX = 0;
	private static final int VALUE_INDEX = 1;
    private IModifiableEnvironmentState myEnvironment;

    public MakeVariableCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
        myEnvironment = aEnvironment;
    }

    @Override
    public double eval (IReadableInput ... aList) throws InvalidNodeUsageException {
        myEnvironment.assignVariable(aList[VARIABLE_NAME_INDEX].getName(), aList[VALUE_INDEX].getValue());
        return aList[VALUE_INDEX].getValue();
    }
}
