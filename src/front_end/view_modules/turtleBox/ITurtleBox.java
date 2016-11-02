package front_end.view_modules.turtleBox;

import front_end.acceptor.IBackgroundAcceptor;
import front_end.acceptor.IColorSenderAcceptor;
import front_end.sender.IColorSender;
import front_end.sender.IRobotSender;
import front_end.view_modules.IRobotAcceptor;
import front_end.view_modules.IViewModule;

public interface ITurtleBox extends IViewModule, IRobotAcceptor, IBackgroundAcceptor, IColorSenderAcceptor {
		
}
