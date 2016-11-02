package front_end.view_modules.turtleBox.turtleMovement;

import back_end.model.robot.IViewableRobot;
import front_end.view_modules.image_color_module.interfaces.IColorModule;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class LineDrawer {
	
	private IViewableRobot myRobot;
	private MovementCalculator myMoveCalc;
	private IColorModule myColorMap;
	private GraphicsContext myGC;
	
	LineDrawer(IViewableRobot aRobot, MovementCalculator aMoveCalc,
				IColorModule aColorMap, GraphicsContext aGC){
		myRobot = aRobot;
		myMoveCalc = aMoveCalc;
		myColorMap = aColorMap;	
		myGC = aGC;
	}
	
	void draw() {
		myGC.setStroke( Color.BLACK ); //myColorMap.getColor(myRobot.getPenInformation().getColorID()));
		myGC.setLineWidth(5);
		myGC.strokeLine(
				myMoveCalc.translateXCoordinate(myRobot.getPreviousCoordinate().getX()),
				myMoveCalc.translateYCoordinate(myRobot.getPreviousCoordinate().getY()),
				myMoveCalc.translateXCoordinate(myRobot.getCurrentCoordinate().getX()), 
				myMoveCalc.translateYCoordinate(myRobot.getCurrentCoordinate().getY()));
	}
	
	
	
	
}
