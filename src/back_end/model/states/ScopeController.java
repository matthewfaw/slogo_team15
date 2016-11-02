package back_end.model.states;

import java.util.List;
import java.util.Stack;

import back_end.model.robot.RobotController;
import back_end.model.robot.Turtle;
import integration.observe.IObservable;
import integration.observe.IObserver;
import integration.observe.Observable;


public class ScopeController {
	
	private Stack<FunctionScope> myScopes;
	private Environment myEnvironment; 
	private List<IObserver> myObservers;
	private RobotController myRobotController;
	
	public ScopeController(Environment aEnvironment, RobotController aRobotController) {	
		myScopes = new Stack<FunctionScope>();
		myEnvironment = aEnvironment;
		myRobotController = aRobotController;

		addNewFunctionScope();
	}

	public void addNewFunctionScope() {
		FunctionScope scope = new FunctionScope();
		myScopes.push(scope);
		myEnvironment.setCurrentScope(scope);
	}
	
	public void removeCurrentFunctionScope() {
		myScopes.pop();
		myEnvironment.setCurrentScope(myScopes.peek());
	}
	
	public void addNestedScope() {
		myEnvironment.addNestedScope();
	}
	
	public void removeNestedScope() {
		myEnvironment.removeNestedScope();
	}
	
	public void setNextTurtleAsActive()
	{
		myRobotController.setNextTurtleAsActive();
	}
	public boolean activeTurtleIndexHasBeenSetToStart()
	{
		return myRobotController.activeTurtleIndexHasBeenSetToStart();
	}
}
