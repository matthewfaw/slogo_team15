package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.states.Scope;


public class ForCommand implements ICommandBranch {

    private boolean myFirst;
    private Scope myScope;

    public ForCommand (Scope aScope) {
        myScope = aScope;
        myFirst = true;
    }

    @Override
    public int evalCondition (IReadableInput ... aList) {
        if (myFirst) {
            myFirst = false;
            myScope.assignVariable(aList[0].getName(), aList[1].getValue());
        }
        if (myScope.getValue(aList[0].getName()) < aList[2].getValue()) {
            myScope.assignVariable(aList[0].getName(),
                                   (myScope.getValue(aList[0].getName()) + aList[3].getValue()));
            return 0;
        }
        return -1;
    }

    @Override
    public double eval (IReadableInput ... aList) {
        return aList[aList.length - 1].getValue();
    }

}
