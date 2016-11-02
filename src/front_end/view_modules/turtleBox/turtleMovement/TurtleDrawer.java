package front_end.view_modules.turtleBox.turtleMovement;

import back_end.model.robot.IViewableRobot;
import front_end.view_modules.image_color_module.interfaces.IImageColorModule;
import integration.observe.IObserver;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;


public class TurtleDrawer implements IObserver {

    private Canvas myCanvas;
    private int myWidth, myHeight;

    private IViewableRobot myRobot;
    private IImageColorModule myImageColorMap;

    private MovementCalculator myMoveCalc;
    private ImageMover myImageMover;
    private LineDrawer myLineDrawer;
    
    private Animation myAnimation;

    TurtleDrawer () {
        myCanvas = new Canvas();
    }

    TurtleDrawer (int aWidth, int aHeight) {
        this();
        myWidth = aWidth;
        myHeight = aHeight;
        myCanvas.setWidth(myWidth);
        myCanvas.setHeight(myHeight);
        myMoveCalc = new MovementCalculator(myWidth, myHeight);

        myCanvas.setWidth(myWidth);
        myCanvas.setHeight(myHeight);
    }

    public TurtleDrawer (IViewableRobot aRobot,
                         IImageColorModule aImageColorMap,
                         int aWidth,
                         int aHeight) {
        this(aWidth, aHeight);
        myRobot = aRobot;
        myRobot.registerObserver(this);
        myImageColorMap = aImageColorMap;

        myImageMover = new ImageMover(myRobot, myImageColorMap, myMoveCalc);
        myLineDrawer = new LineDrawer(myRobot, myMoveCalc, myImageColorMap,
                                      myCanvas.getGraphicsContext2D());

    }
    

    @Override
    public void update () {
        myLineDrawer.draw();
        myAnimation = myImageMover.move();
        myAnimation.play();
       /* Path path = new Path();
        path.getElements().addAll(new MoveTo(500, 500));
        // create an animation where the shape follows a path
        PathTransition pt = new PathTransition(Duration.millis(4000), path, myImageMover.getInstanceAsNode());
        // create an animation that rotates the shape
        RotateTransition rt = new RotateTransition(Duration.seconds(3));
        rt.setByAngle(90);
        myAnimation = new SequentialTransition(myImageMover.getInstanceAsNode(), pt, rt);
        myAnimation.play();*/
    }
    
    public Animation returnAnimation() {
        if (myAnimation == null) 
            return new SequentialTransition();
        else 
            return myAnimation;
    }

    public Node getCanvas () {
        return myCanvas;
    }

    public Node getImage () {
        return myImageMover.getInstanceAsNode();
    }
}
