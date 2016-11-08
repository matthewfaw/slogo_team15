package back_end.model.states;

import java.util.Collection;

import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.robot.Turtle;
import back_end.model.states.background.BackgroundInformation;
import back_end.model.states.background.IModifiableBackground;

/**
 * This is the interface for the EnviromentState. The Command classes get access to this interface to make changes to the current
 * Environment at the runtime of some user input.
 * 
 *
 */

public interface IModifiableEnvironmentState extends IViewableVariableState, IModifiableBackground, IModifiableMethodState {
	
	/**
	 * Checks if the function contains a variable 
	 * @param aVariableName
	 * @return
	 */
	public boolean containsVariable(String aVariableName);
	
	
	/**
	 * Assigns a variable to the current function
	 * 
	 * @param aName
	 * @param aValue
	 */
	public void assignVariable(String aName, double aValue);
	
	
	/**
	 * Gets the current variables in the method - this is used for custom commands to get the variables associated to
	 * the method name
	 * 
	 * @param aMethodName
	 * @param aValues
	 * @throws InvalidNodeUsageException
	 */
	public void getVariablesInMethod(String aMethodName, Double...aValues) throws InvalidNodeUsageException;
	
	/**
	 * Gets the current Background information class so that it can be modified
	 * 
	 * @return
	 */
	public BackgroundInformation getBackgroundInformation();

	/**
	 * Removes all active Turtles
	 */
	public void clearActiveTurtles();
	
	/**
	 * Adds a Turtle ID to the active turtles
	 * @param aTotalTurtleID
	 */
	public void addActiveTurtle(int aTotalTurtleID);
	
	/**
	 * Adds a turtle to the screen
	 * @param aTurtleID
	 */
	public void addTurtle(int aTurtleID);
	
	/**
	 * Gets the last Turtle from the active turtle list
	 * @return
	 */
	public Turtle getCurrentTurtle();
	
	/**
	 * Returns the list of currently active turtles
	 * @return
	 */
	public Collection<Turtle> getCurrentlyActiveTurtles();
	
	/**
	 * Sets the currently active turtle
	 * @param aActiveTurtleIndex
	 */
	public void setTurtleAsCurrentlyActive(int aActiveTurtleIndex);
	
	/**
	 * Returns in turtle exists in Turtles on screen
	 * 
	 * @param aTurtleIndex
	 * @return
	 */
	public boolean containsTurtle(int aTurtleIndex);
	
	
	/**
	 * Gets the number of Turtles on the screen
	 * 
	 * @return
	 */
	public int numberOfTurtlesCreated();
	
	/**
	 * Sets the Palette information 
	 * 
	 * @param aIndex
	 * @param aRed
	 * @param aBlue
	 * @param aGreen
	 */
	public void setPaletteColors(int aIndex, int aRed, int aBlue, int aGreen);
	
	
	
}
