package front_end.view_modules.turtleBox;

import back_end.model.robot.IViewRobot;
import front_end.acceptor.IRobotAcceptor;
import front_end.view_modules.IViewModule;
import integration.observe.IObserver;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;


public interface ITurtleBox extends IViewModule, IObserver, IRobotAcceptor {

    public void initBox (int aWidth, int aHeight);

    public GraphicsContext getGC ();

    public ImageView getTurtle ();

    public double getTurtleHeight ();

    public double getTurtleWidth ();

    public IViewRobot getRobot ();

    public void removeTurtle ();

    public void showTurtle ();
}
