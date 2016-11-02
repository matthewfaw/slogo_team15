package front_end.view_modules.turtleBox.turtleMovement;

import back_end.model.robot.IViewableRobot;
import front_end.view_modules.image_color_module.interfaces.IImageColorModule;
import integration.observe.IObserver;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;

public class TurtleDrawer implements IObserver {

	private Canvas myCanvas;
	private int myWidth, myHeight;

	private IViewableRobot myRobot;
	private IImageColorModule myImageColorMap;

	private MovementCalculator myMoveCalc;
	private ImageMover myImageMover;
	private LineDrawer myLineDrawer;

	TurtleDrawer(){
		myCanvas = new Canvas();
	}

	TurtleDrawer(int aWidth, int aHeight){
		this();
		myWidth = aWidth;
		myHeight = aHeight;
		myCanvas.setWidth(myWidth);
		myCanvas.setHeight(myHeight);
		myMoveCalc = new MovementCalculator(myWidth, myHeight);

		myCanvas.setWidth(myWidth);
		myCanvas.setHeight(myHeight);
	}

	public TurtleDrawer( IViewableRobot aRobot, IImageColorModule aImageColorMap, int aWidth, int aHeight) {
		this(aWidth, aHeight);
		myRobot = aRobot;
		myRobot.registerObserver(this);
		myImageColorMap = aImageColorMap;

		myImageMover = new ImageMover(myRobot, myImageColorMap, myMoveCalc);
		myLineDrawer = new LineDrawer(myRobot, myMoveCalc, myImageColorMap, 
				myCanvas.getGraphicsContext2D());
		
	}

	@Override
	public void update() {
		myLineDrawer.draw();
		myImageMover.move();
	}

	public Node getCanvas(){
		return myCanvas;
	}

	public Node getImage(){
		return myImageMover.getImage();
	}
}