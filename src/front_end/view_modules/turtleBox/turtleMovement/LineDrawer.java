package front_end.view_modules.turtleBox.turtleMovement;
import back_end.model.robot.IViewableRobot;
import front_end.view_modules.image_color_module.interfaces.IColorModule;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;

class LineDrawer implements IDrawer {
        
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
                if(!myRobot.getPenInformation().isPenUp()) drawDefault();
        }
        
        void drawDefault() {
                myGC.setStroke( myColorMap.getColor(myRobot.getPenInformation().getColorID()) ); //myColorMap.getColor(myRobot.getPenInformation().getColorID()));
                myGC.setLineWidth(myRobot.getPenInformation().getPenThickness());
                myGC.strokeLine(
                                myMoveCalc.translateXCoordinate(myRobot.getPreviousCoordinate().getX()),
                                myMoveCalc.translateYCoordinate(myRobot.getPreviousCoordinate().getY()),
                                myMoveCalc.translateXCoordinate(myRobot.getCurrentCoordinate().getX()), 
                                myMoveCalc.translateYCoordinate(myRobot.getCurrentCoordinate().getY()));
        }

        @Override
        public void reset () {
            // TODO Auto-generated method stub
            myGC.clearRect(0, 0, myGC.getCanvas().getWidth(), myGC.getCanvas().getHeight());
        }

        @Override
        public Node getInstanceAsNode () {
            return myGC.getCanvas();
        }
        
        
}
