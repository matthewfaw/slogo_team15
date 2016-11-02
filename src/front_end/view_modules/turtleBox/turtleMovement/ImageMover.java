package front_end.view_modules.turtleBox.turtleMovement;

import back_end.model.robot.IViewableRobot;
import front_end.view_modules.image_color_module.interfaces.IImageModule;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageMover {
	private ImageView myImage;
	private IViewableRobot myRobot;
	private IImageModule myImageMap;
	
	private ImageMover(){
		myImage = new ImageView();
	}
	
	ImageMover(IViewableRobot aRobot, IImageModule aImageMap) {
		this();
		myRobot = aRobot;
		myImageMap = aImageMap;
		loadImage();
	}
	
	public Node getImage() {
		return myImage;
	};
	
	private void loadImage() {
		myImage.setImage( new Image(myImageMap.getFile(myRobot.getImageID()).toURI().toString()) );
	}
	
}
