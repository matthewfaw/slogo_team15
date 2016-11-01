package front_end.view_modules.errorViewer;

import front_end.acceptor.IErrorAcceptor;
import front_end.view_modules.IViewModule;


/**
 * Contains ErrorStructure that listens for changes seen by the Mediator
 * And then updates any line by line or syntax errors.
 * 
 * @author George Bernard
 */
public interface IErrorViewer extends IViewModule, IErrorAcceptor {

}
