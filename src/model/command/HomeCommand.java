package model.command;

import model.node.ConstantNode;
import model.node.IReadableInput;
import model.robot.Robot;

public class HomeCommand extends SetPositionCommand {

	public HomeCommand(Robot aRobot) {
		super(aRobot);
	}
	
	@Override
	public double eval(IReadableInput... aList) {
		IReadableInput[] zeroList = {(IReadableInput) new ConstantNode(0), (IReadableInput) new ConstantNode(0)}; 
		return super.eval(zeroList);
	}

}
