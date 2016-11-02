package back_end.model.states;

import java.util.List;
import java.util.Stack;

import integration.observe.IObserver;
import integration.observe.Observable;


public class ScopeController extends Observable {
	
	private Stack<FunctionScope> myScopes;
	private Environment myEnvironment; 
	
	public ScopeController() {	
		myScopes = new Stack<FunctionScope>();
		myEnvironment = Environment.getInstance();
		
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

}
