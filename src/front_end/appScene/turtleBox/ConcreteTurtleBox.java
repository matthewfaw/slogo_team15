package front_end.appScene.turtleBox;

import back_end.model.robot.IViewRobot;
import integration.languages.Languages;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

class ConcreteTurtleBox implements ITurtleBox {

	ScrollPane myScroller;
	Pane mySandbox;
	ColorPicker myBackgroundColorPicker;
	IViewRobot myRobot;
	ImageView myTurtle;
	Group root;
	Canvas drawingCanvas;
	GraphicsContext gc;
	TurtleMovement myTurtMove = new TurtleMovement(this);

	private int myWidth;
	private int myHeight;
	private final int CHARACTER_SIZE = 50;
	
	ConcreteTurtleBox(int width, int height){
	        myWidth = width;
	        myHeight = height;
	        root = new Group();
	        drawingCanvas = new Canvas(width, height);
	        gc = drawingCanvas.getGraphicsContext2D();
		myScroller = new ScrollPane();
		myScroller.setPrefSize(width, height);
		myScroller.setMinSize(width, height);
		myScroller.setMaxSize(width, height);
		
		mySandbox = new Pane();
                mySandbox.setPrefSize(width, height);
                mySandbox.setMinSize(width, height);
                mySandbox.setMaxSize(width, height);

		myScroller.setContent(mySandbox);
                testDraw(gc);
                initBox(width, height);
		
	}
	
	private void testDraw(GraphicsContext gc) {
	        gc.setStroke(Color.BLUE);
	        gc.setLineWidth(5);
	        gc.strokeLine(100, 120, 120, 240);
	        mySandbox.getChildren().add(drawingCanvas);
	}
	
	public GraphicsContext getGC() {
	    return gc;
	}
	
	public ImageView getTurtle() {
	    return myTurtle;
	}
	
	public void initBox(int width, int height) {
	           initColorPicker();
	           loadDefaultTurtle(width, height);
	}
	
	public void loadDefaultTurtle(int width, int height) {
	    Image character = new Image(getClass().getClassLoader().getResourceAsStream("turtle.png"));
	    myTurtle = new ImageView(character);
	    myTurtle.setFitHeight(CHARACTER_SIZE);
	    myTurtle.setFitWidth(CHARACTER_SIZE);
	    myTurtle.setX(width/2);
	    myTurtle.setY(height/2);
	    mySandbox.getChildren().add(myTurtle);
	}
	
	@Override
	public void reset() {
	    //TODO: Get magic numbers out of here
	    mySandbox.getChildren().clear();
	    initBox(myWidth, myHeight);
	}

	@Override
	public Node getInstanceAsNode() {
		return myScroller;
	}

	@Override
	public void giveRobot(IViewRobot aRobot) {
		myRobot = aRobot;

	}
	
	public IViewRobot getRobot() {
	    return myRobot;
	}
	
	@Override
	public void update() {
		if(myRobot == null) {
		    System.out.println("cats");
		    return;
		}
		
		myTurtMove.updateTurtle();
		System.out.println(myRobot.getY());
		
	}
	
	private void initColorPicker(){
		myBackgroundColorPicker = new ColorPicker();
                myBackgroundColorPicker.setOnAction(
                                                    e -> mySandbox.setBackground( new Background( new BackgroundFill(
                                                                                                    myBackgroundColorPicker.getValue(),
                                                                                                    CornerRadii.EMPTY, 
                                                                                                    Insets.EMPTY
                                                                                                    ))));
		mySandbox.getChildren().add(myBackgroundColorPicker);
	}

	@Override
	public void switchLanguage(Languages aLanguage) {
		// TODO Auto-generated method stub
		
	}


}
