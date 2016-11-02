package front_end.view_modules.turtleBox.turtleMovement;

import back_end.model.robot.IViewableRobot;
import front_end.view_modules.image_color_module.interfaces.IImageColorModule;
import integration.observe.IObserver;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class TurtleDrawer implements IObserver {

	private Canvas myCanvas;
	private GraphicsContext myGC;
	private int myWidth, myHeight;
	
	private IViewableRobot myRobot;
	private IImageColorModule myImageColorMap;
	
	private MovementCalculator myCoordCalc;
	private ImageMover myImageMover;
	private LineDrawer myLineDrawer;
	
	TurtleDrawer(){
		myCanvas = new Canvas();
	}
	
	TurtleDrawer(int aWidth, int aHeight){
		this();
		myWidth = aWidth;
		myHeight = aHeight;
		myCoordCalc = new MovementCalculator(myWidth, myHeight);
		
		myCanvas.setWidth(myWidth);
		myCanvas.setHeight(myHeight);
		myGC = myCanvas.getGraphicsContext2D();
	}
	
	TurtleDrawer( IViewableRobot aRobot, IImageColorModule aImageColorMap, 
				  int aWidth, int aHeight) {
		this(aWidth, aHeight);
		myRobot = aRobot;
		myRobot.registerObserver(this);
		myImageColorMap = aImageColorMap;
		
		
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	Node getAsNod(){
		return myCanvas;
	}
	
}