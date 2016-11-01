package back_end.model.states;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.IReadableInput;
import back_end.model.states.background.BackgroundInformation;
import back_end.model.states.background.IModifiableBackground;
import integration.observe.Observable;

/**
 * A singleton class that keeps track of current variables and methods associated with evaluation
 * 
 * From http://www.oodesign.com/singleton-pattern.html:
 * Usually singletons are used for centralized management of internal or external resources and they provide a global point of access to themselves
 * 
 * Making this class a singleton guarantees that everyone has the same instance of the current scope, so everything is consistent
 * 
 * @author hannahfuchshuber && matthewfaw
 *
 */


public class Environment extends Observable implements IModifiableEnvironmentState, IViewableVariableState, IModifiableBackground {

	public static final Environment INSTANCE = new Environment();
	
	private FunctionScope myCurrentScope;
	private Map<String, Method> myMethodMap;
	private BackgroundInformation myBackgroundInformation;
	
	private Environment() {
		myMethodMap = new HashMap<String, Method>();
		myBackgroundInformation = new BackgroundInformation();
	}
	
	public static Environment getInstance() {
		return INSTANCE;
	}
	
	/**SCOPE CONTROLLING**/
	
	void addNestedScope() {
		myCurrentScope.addNestedScope();
	}
	
	
	void removeNestedScope() {
		myCurrentScope.removeNestedScope();
	}
	
	public void setCurrentScope(FunctionScope aCurrentScope) {
		myCurrentScope = aCurrentScope;
	}
	
	/**VARIABLES**/
	
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
	
	public void assignMethod(String aMethodName, IReadableInput aNode, IReadableInput...aVariableInputs) {
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
	
	@Override
	public int getBackgroundColor() {
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


}
