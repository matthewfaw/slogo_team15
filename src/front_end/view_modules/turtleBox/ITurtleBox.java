package front_end.view_modules.turtleBox;

import back_end.model.robot.IViewableRobot;
import front_end.view_modules.IRobotAcceptor;
import front_end.view_modules.IViewModule;
import integration.observe.IObserver;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;

public interface ITurtleBox extends IViewModule, IRobotAcceptor {

    public void initBox (int aWidth, int aHeight);

    public GraphicsContext getGC ();

    public ImageView getTurtle ();

    public double getTurtleHeight ();

    public double getTurtleWidth ();

    public IViewableRobot getRobot ();

    public void removeTurtle ();

    public void showTurtle ();
}
