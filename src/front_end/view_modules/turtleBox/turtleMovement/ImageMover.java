package front_end.view_modules.turtleBox.turtleMovement;

import back_end.model.robot.IViewableRobot;
import front_end.view_modules.image_color_module.interfaces.IImageModule;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;


public class ImageMover implements IDrawer {
    private ImageView myImageView;
    private Image myImage;
    private IViewableRobot myRobot;
    private IImageModule myImageMap;
    private MovementCalculator myMoveCalc;

    private final int CHARACTER_SIZE = 50;

    private ImageMover () {
        myImageView = new ImageView();
        myImageView.setFitWidth(CHARACTER_SIZE);
        myImageView.setFitHeight(CHARACTER_SIZE);
    }

    private ImageMover (int aWidth, int aHeight) {
        this();
        myImageView.setX(aWidth / 2 - myImageView.getFitWidth() / 2);
        myImageView.setY(aHeight / 2 - myImageView.getFitHeight() / 2);
    }

    ImageMover (IViewableRobot aRobot, IImageModule aImageMap, MovementCalculator aMoveCalc) {
        this(aMoveCalc.getWidth(), aMoveCalc.getHeight());
        myRobot = aRobot;
        myImageMap = aImageMap;
        myMoveCalc = aMoveCalc;
        loadImage();
    }

    public SequentialTransition move (double animationSpeed, double maxAnimationSpeed) {
        loadImage();
        checkVisibility();
        if(animationSpeed == maxAnimationSpeed) {
            myImageView.setX(currentXCoordinate()-myImageView.getFitWidth() / 2);
            myImageView.setY(currentYCoordinate()-myImageView.getFitHeight() / 2);
            myImageView.setRotate(-myRobot.getCurrentRotation());
            return new SequentialTransition(myImageView);
        }
        else {
            if (previousXCoordinate() == currentXCoordinate() &&
                    previousYCoordinate() == currentYCoordinate()) {
                return new SequentialTransition(myImageView, rotate(animationSpeed, maxAnimationSpeed));
            }
            else {
                return new SequentialTransition(myImageView, translate(animationSpeed, maxAnimationSpeed), rotate(animationSpeed, maxAnimationSpeed));
            }
        }
    }

    private void loadImage () {
        myImage = new Image(myImageMap.getFile(myRobot.getImageID()).toURI().toString());
        myImageView.setImage(myImage);
    }

    private void checkVisibility () {
        if (myRobot.isVisible())
            myImageView.setImage(myImage);
        else
            myImageView.setImage(null);
    }

    private PathTransition translate (double animationSpeed, double maxAnimationSpeed) {
        Path path = new Path();
        PathTransition pt = new PathTransition();
        
        MoveTo myMove = new MoveTo(previousXCoordinate(), previousYCoordinate());
        LineTo myLine = new LineTo(currentXCoordinate(), currentYCoordinate());
        path.getElements().addAll(myMove, myLine);
        pt = new PathTransition(Duration.seconds(1/animationSpeed), path, myImageView);

        return pt;
    }
    
    private RotateTransition rotate (double animationSpeed, double maxAnimationSpeed) {
        RotateTransition rt = new RotateTransition();
        if (myRobot.getCurrentRotation() != myRobot.getPreviousRotation()) {
            rt = new RotateTransition(Duration.seconds(1/animationSpeed));
            rt.setByAngle(-myRobot.getCurrentRotation() + myRobot.getPreviousRotation());
        }
        else {
            rt = new RotateTransition(Duration.seconds(0));
        }
        return rt;
    }

    private double previousXCoordinate () {
        return myMoveCalc.translateXCoordinate(myRobot.getPreviousCoordinate().getX());
    }

    private double previousYCoordinate () {
        return myMoveCalc.translateYCoordinate(myRobot.getPreviousCoordinate().getY());
    }

    private double currentXCoordinate () {
        return myMoveCalc.translateXCoordinate(myRobot.getCurrentCoordinate().getX());
    }

    private double currentYCoordinate () {
        return myMoveCalc.translateYCoordinate(myRobot.getCurrentCoordinate().getY());
    }

    @Override
    public void reset () {
        // TODO Auto-generated method stub

    }

    @Override
    public Node getInstanceAsNode () {
        return myImageView;
    }
}
