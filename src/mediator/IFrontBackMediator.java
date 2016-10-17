package mediator;

/**
 * @author George Bernard
 *
 * Keystone of the Model-View-Adapter Framework. 
 * The Mediator comes from the mediator design pattern, 
 * this helps to directly disconnect the model from the view, 
 * rather than the model updating the view which is true in 
 * traditional MVC.
 *
 */
public interface IFrontBackMediator {

	/************  Methods that Interact with Front-end  **************/
	
	/**
	 * @param FrontEnd
	 */
	public void giveFrontInstance(Object FrontEnd);
	
	/**
	 * When updated from Front-end, call pushToBack Methods 
	 * if anything has changed
	 */
	public void updateFromFront();

	public void pushRobotUpdate();
	
	public void pushVariableUpdate();
	
	public void pushErrorUpdate();
	
	public void pushCommandUpdate();
	
	/************  Methods that Interact with Back-end  **************/
	
	/**
	 * @param BackEnd - an instance of the model class
	 */
	public void giveBackInstance(Object BackEnd);
	
	/**
	 * when updated from back-end, call pushToFront methods 
	 * if anything has changed
	 */
	public void updateFromBack();
	
	public void pushCodeEvaluationUpdate();
	
	
}
