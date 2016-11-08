package back_end.model.states;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.IReadableInput;
import back_end.model.robot.RobotController;
import back_end.model.robot.Turtle;
import back_end.model.states.background.BackgroundInformation;
import back_end.model.states.background.IModifiableBackground;
import integration.observe.AbstractObservable;

/**
 * A class that keeps track of current variables and methods associated with evaluation also general background information
 * 
 * This class can be thought of as a class the houses information that needs to be kept even after the end of the running 
 * the command
 * 
 * More commenting on the specific methods can be found inside each of the classes that this class calls from.
 * 
 * @author hannahfuchshuber && matthewfaw
 *
 */


public class Environment extends AbstractObservable implements IModifiableEnvironmentState, IViewableVariableState, IModifiableBackground {

	private FunctionScope myCurrentScope;
	private Map<String, Method> myMethodMap;
	private BackgroundInformation myBackgroundInformation;
	private RobotController myRobotController;
	
	public Environment(RobotController aRobot) {
		myMethodMap = new HashMap<String, Method>();
		myBackgroundInformation = new BackgroundInformation();
		myRobotController = aRobot;
	}
	
	/**SCOPE CONTROLLING**/
	
	/**
	 * The following methods use the FunctionScope class - and keep track of the current FunctionScope
	 */
	void addNestedScope() {
		myCurrentScope.addNestedScope();
		notifyObservers();
	}
	
	
	void removeNestedScope() {
		myCurrentScope.removeNestedScope();
		notifyObservers();
	}
	
	public void setCurrentScope(FunctionScope aCurrentScope) {
		myCurrentScope = aCurrentScope;
	}
	
	/**VARIABLES**/
	
	/**
	 * The following methods use the FunctionScope class to keep track of the variables currently in the Scope.
	 */
	
	public boolean containsVariable(String name) {
		return myCurrentScope.containsVariable(name);
	}

	public void assignVariable(String aName, double aValue) {
		myCurrentScope.assignVariable(aName, aValue);
		notifyObservers();
	}
	
	@Override
	public Collection<String> getVariableKeySet() {
		return myCurrentScope.getVariableKeySet();
	}
	
	@Override
	public double getValue(String aVariable) {
		return myCurrentScope.getVariableValue(aVariable);
	}
	
	/**METHOD**/
	
	/**
	 * The following methods use the method map to keep track of the current methods
	 */
	
	public void assignMethod(String aMethodName, IReadableInput[] aVariableInputs, IReadableInput aNode) {
		Method methodState = new Method();
		methodState.assignMethod(aMethodName, aNode, aVariableInputs);
		myMethodMap.put(aMethodName, methodState);
	}
	
	public Collection<IReadableInput> getMethodVariables(String aMethodName) {
		return myMethodMap.get(aMethodName).getVariables();
	}
	
	public IReadableInput getMethodExecutionNode(String aMethodName) {
		return myMethodMap.get(aMethodName).getExecutionNode();
	}
	
	public Collection<String> getAllMethodNames() {
		return myMethodMap.keySet();
	}
	
	public void clearMethods() {
		myMethodMap = new HashMap<String, Method>();
	}
	
	public void getVariablesInMethod(String aMethodName, Double...aValues) throws InvalidNodeUsageException {
		Method currentMethodState = myMethodMap.get(aMethodName);
		List<IReadableInput> arrayOfVariables = currentMethodState.getVariables();
		for (int i = 0; i < arrayOfVariables.size(); i++) {
			myCurrentScope.assignVariable(arrayOfVariables.get(i).getName(), aValues[i]);
		}	
	}
	
	/**BACKGROUND INFORMATION**/
	
	/**
	 * The following methods call the BackgroundInformation class to get information on the current background information
	 */
	
	@Override
	public String getBackgroundColor() {
		return myBackgroundInformation.getBackgroundColor();
	}
	
	@Override
	public void setBackgroundColor(int aColor) {
		myBackgroundInformation.setBackgroundColor(aColor);
	}
	
	@Override
	public BackgroundInformation getBackgroundInformation() {
		return myBackgroundInformation;
	}
	
	/**TURTLE INFORMATION**/
	
	/**
	 * The following methods use the RobotController class to get information on the current Turtle
	 */
	
	public void clearActiveTurtles() {
		myRobotController.clearActiveTurtles();
	}
	
	public void addActiveTurtle(int aTurtleID) {
		myRobotController.addActiveTurtle(aTurtleID);
	}
	
	public void addTurtle(int aTurtleID) {
		myRobotController.addTurtle(aTurtleID);
	}
	public Turtle getCurrentTurtle() {
		return myRobotController.getCurrentTurtle();
	}
	
	public Collection<Turtle> getCurrentlyActiveTurtles() {
		return myRobotController.getCurrentlyActiveTurtles();
	}
	
	public void setTurtleAsCurrentlyActive(int aActiveTurtleIndex) {
		myRobotController.setTurtleAsCurrentlyActive(aActiveTurtleIndex);
	}
	
	public boolean containsTurtle(int aTurtleIndex) {
		return myRobotController.containsTurtles(aTurtleIndex);
	}
	
	public int numberOfTurtlesCreated() {
		return myRobotController.numberOfTurtlesCreated();
	}
	
	public void setPaletteColors(int aIndex, int aRed, int aBlue, int aGreen) {
		myBackgroundInformation.setPaletteColor(aIndex, aRed, aBlue, aGreen);
	}

}
