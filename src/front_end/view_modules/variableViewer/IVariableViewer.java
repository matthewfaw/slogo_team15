package front_end.view_modules.variableViewer;

import front_end.acceptor.IVariableAcceptor;
import front_end.view_modules.IViewModule;
import integration.observe.IObserver;


/**
 * This interface serves as a multi-face set 
 * of interfaces for any Variable Viewer to implement
 * 
 * @see IViewModule
 * @see IVariableAcceptor
 * @see IObserver
 * @author George Bernard
 */
public interface IVariableViewer extends IViewModule, IVariableAcceptor, IObserver {


}
