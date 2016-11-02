package front_end.view_modules.turtleBox.turtleMovement;

import back_end.model.robot.IViewableRobot;
import front_end.view_modules.image_color_module.interfaces.IImageColorModule;
import front_end.view_modules.turtleBox.TurtleBoxToolbar;
import integration.observe.IObserver;
import javafx.animation.Animation;
import javafx.animation.SequentialTransition;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;


public class TurtleDrawer implements IObserver {

    private Canvas myCanvas;
    private TurtleBoxToolbar myTurtleBoxToolbar;
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
                         int aHeight,
                         TurtleBoxToolbar aTurtleBoxToolbar) {
        this(aWidth, aHeight);
        myRobot = aRobot;
        myRobot.registerObserver(this);
        myImageColorMap = aImageColorMap;
        myTurtleBoxToolbar = aTurtleBoxToolbar;

        myImageMover = new ImageMover(myRobot, myImageColorMap, myMoveCalc);
        myLineDrawer = new LineDrawer(myRobot, myMoveCalc, myImageColorMap,
                                      myCanvas.getGraphicsContext2D());

    }
    

    @Override
    public void update () {
        double animationSpeed = myTurtleBoxToolbar.getMyAnimationSliderValue();
        double maxAnimationSpeed = myTurtleBoxToolbar.getMaxSliderValue();
        myLineDrawer.draw();
        myAnimation = myImageMover.move(animationSpeed, maxAnimationSpeed);
        myAnimation.play();
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
