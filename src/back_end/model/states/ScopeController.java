package back_end.model.states;

import java.util.Stack;

import back_end.model.robot.RobotController;
import back_end.model.robot.Turtle;
import integration.observe.IObservable;
import integration.observe.IObserver;
import integration.observe.AbstractObservable;

/**
 * @author matthewfaw
 * 
 * Updates and keeps track of the current scope - aka the variables that are currently active and which variables can be 
 * "popped off"
 *
 *
 */

public class ScopeController {
	
	private Stack<FunctionScope> myScopes;
	private Environment myEnvironment; 
	private RobotController myRobotController;
	
	public ScopeController(Environment aEnvironment, RobotController aRobotController) {	
		myScopes = new Stack<FunctionScope>();
		myEnvironment = aEnvironment;
		myRobotController = aRobotController;

		addNewFunctionScope();
	}

	/**
	 * Replaces the currently active variables with an entirely new scope - aka a new function call
	 * 
	 */
	public void addNewFunctionScope() {
		FunctionScope scope = new FunctionScope();
		myScopes.push(scope);
		myEnvironment.setCurrentScope(scope);
	}
	
	/**
	 * Removes the Current Function Scope entirely 
	 */
	public void removeCurrentFunctionScope() {
		myScopes.pop();
		myEnvironment.setCurrentScope(myScopes.peek());
	}
	
	/**
	 * Adds a nested scope
	 */
	public void addNestedScope() {
		myEnvironment.addNestedScope();
	}
	
	/**
	 * Removes a nested scope
	 */
	public void removeNestedScope() {
		myEnvironment.removeNestedScope();
	}
	
	/**
	 * Uses the RobotController to set the next active turtle
	 */
	public void setNextTurtleAsActive()
	{
		myRobotController.setNextTurtleAsActive();
	}
	
	/**Sees if the active Turtle list is empty
	 * 
	 * @return
	 */
	public boolean activeTurtleIndexHasBeenSetToStart()
	{
		return myRobotController.activeTurtleIndexHasBeenSetToStart();
	}
	
	/**
	 * Adds a TurtleScope
	 */
	public void addTemporaryTurtleScope()
	{
		myRobotController.addTemporaryTurtleScope();
	}
	
	/**
	 * Removes a Turtle Scope
	 */
	public void removeTemporaryTurtleScope()
	{
		myRobotController.removeTemporaryTurtleScope();
	}
}