package back_end.model.states;

import java.util.Collection;
import java.util.List;

import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.robot.Turtle;
import back_end.model.states.background.BackgroundInformation;
import back_end.model.states.background.IModifiableBackground;

public interface IModifiableEnvironmentState extends IViewableVariableState, IModifiableBackground, IModifiableMethodState {
	
	public boolean containsVariable(String aVariableName);
	
	public void assignVariable(String aName, double aValue);
	
	public void getVariablesInMethod(String aMethodName, Double...aValues) throws InvalidNodeUsageException;
	
	public BackgroundInformation getBackgroundInformation();

	public void clearActiveTurtles();
	
	public void addActiveTurtle(int aTotalTurtleID);
	
	public void addTurtle(int aTurtleID);
	
	public Turtle getCurrentTurtle();
	
	public Collection<Turtle> getCurrentlyActiveTurtles();
	
	public void setTurtleAsCurrentlyActive(int aActiveTurtleIndex);
	
	public boolean containsTurtle(int aTurtleIndex);
	
	public int numberOfTurtlesCreated();
	
	
	
}
