package front_end.view_modules.variableViewer;

import front_end.view_modules.ILanguageSwitcher;
import front_end.view_modules.IViewModule;
import front_end.view_modules.IViewVariableAcceptor;
import integration.observe.IObserver;


public interface IVariableViewer extends IViewModule, ILanguageSwitcher, IObserver, IViewVariableAcceptor {

}
