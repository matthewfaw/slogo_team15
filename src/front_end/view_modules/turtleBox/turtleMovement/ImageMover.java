package front_end.view_modules.turtleBox.turtleMovement;

import back_end.model.robot.IViewableRobot;
import front_end.view_modules.image_color_module.interfaces.IImageModule;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageMover implements IDrawer {
	private ImageView myImageView;
	private Image myImage;
	private IViewableRobot myRobot;
	private IImageModule myImageMap;
	private MovementCalculator myMoveCalc;
	
    private final int CHARACTER_SIZE = 50;
	
	private ImageMover() {
		myImageView = new ImageView();
		myImageView.setFitWidth(CHARACTER_SIZE);
		myImageView.setFitHeight(CHARACTER_SIZE);
	}
	
	private ImageMover(int aWidth, int aHeight){
		this();
		myImageView.setX(aWidth / 2);
		myImageView.setY(aHeight / 2);
	}
	
	ImageMover(IViewableRobot aRobot, IImageModule aImageMap, MovementCalculator aMoveCalc) {
		this(aMoveCalc.getWidth(), aMoveCalc.getHeight());
		myRobot = aRobot;
		myImageMap = aImageMap;
		myMoveCalc = aMoveCalc;
		loadImage();
	}
	
	public void move() {
		loadImage();
		checkVisibility();
		translate();
		rotate();
	}
	
	private void loadImage() {
		myImage = new Image(myImageMap.getFile(myRobot.getImageID()).toURI().toString());
		myImageView.setImage( myImage );
	}
	
	private void checkVisibility() {
		if(myRobot.isVisible()) myImageView.setImage(myImage);
		else
		    myImageView.setImage(null);
	}
	
	private void translate(){
		myImageView.setX(myMoveCalc.translateXCoordinate(myRobot.getCurrentCoordinate().getX()));
		myImageView.setY(myMoveCalc.translateYCoordinate(myRobot.getCurrentCoordinate().getY()));
	}
	
	private void rotate(){
		myImageView.setRotate(-myRobot.getCurrentRotation());
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
