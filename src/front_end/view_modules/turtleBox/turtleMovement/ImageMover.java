package front_end.view_modules.turtleBox.turtleMovement;

import back_end.model.robot.IViewableRobot;
import front_end.view_modules.image_color_module.interfaces.IImageModule;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.HLineTo;
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
	private Path myPath;
	
    private final int CHARACTER_SIZE = 50;
	
	private ImageMover() {
		myImageView = new ImageView();
		myImageView.setFitWidth(CHARACTER_SIZE);
		myImageView.setFitHeight(CHARACTER_SIZE);
	}
	
	private ImageMover(int aWidth, int aHeight){
		this();
		myImageView.setX(aWidth / 2- myImageView.getFitWidth()/2);
		myImageView.setY(aHeight / 2- myImageView.getFitHeight()/2);
	}
	
	ImageMover(IViewableRobot aRobot, IImageModule aImageMap, MovementCalculator aMoveCalc) {
		this(aMoveCalc.getWidth(), aMoveCalc.getHeight());
		myRobot = aRobot;
		myImageMap = aImageMap;
		myMoveCalc = aMoveCalc;
		loadImage();
	}
	
	public SequentialTransition move() {
		loadImage();
		checkVisibility();
		//translate();
		//rotate();
		return new SequentialTransition(myImageView, rotate(), translate());
	}
	
	private void loadImage() {
		myImage = new Image(myImageMap.getFile(myRobot.getImageID()).toURI().toString());
		myImageView.setImage( myImage );
	}
	
	private void checkVisibility() {
		if(myRobot.isVisible()) 
		    myImageView.setImage(myImage);
		else
		    myImageView.setImage(null);
	}
	
	private PathTransition translate(){
	        Path path = new Path();
	        MoveTo myMove = new MoveTo(myMoveCalc.translateXCoordinate(myRobot.getPreviousCoordinate().getX()),myMoveCalc.translateYCoordinate(myRobot.getPreviousCoordinate().getY()));
	        LineTo myLine = new LineTo(myMoveCalc.translateXCoordinate(myRobot.getCurrentCoordinate().getX()),myMoveCalc.translateYCoordinate(myRobot.getCurrentCoordinate().getY()));
	        path.getElements().addAll(myMove);
	        path.getElements().add(myLine);
	        PathTransition pt = new PathTransition(Duration.millis(4000), path, myImageView);
	        //myImageView.setX(myMoveCalc.translateXCoordinate(myRobot.getCurrentCoordinate().getX()));
		//myImageView.setY(myMoveCalc.translateYCoordinate(myRobot.getCurrentCoordinate().getY()));
	        return pt;
	}
	
	private RotateTransition rotate(){
	    
	    RotateTransition rt = new RotateTransition(Duration.seconds(3));
	    rt.setByAngle(-myRobot.getRotation());
	    //myImageView.setRotate(-myRobot.getRotation);
	    return rt;
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
