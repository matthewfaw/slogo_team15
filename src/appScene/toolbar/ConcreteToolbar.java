package appScene.toolbar;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.event.EventHandler;

public class ConcreteToolbar implements IToolbar {

    private HBox myToolbar;
    private Button myRun;
    private Button myHelp;
    private Button myCompile;
    private Button myLanguage;
    private Button myStep;
    private Button myClear;
    /**
     *
     * 
     * @param height
     * @param width
     * @return TODO
     */
    public Node getToolbarAsNode (int height, int width) {
        myToolbar = new HBox(0);
        myRun = new Button("Run");
        myHelp = new Button("Help");
        myCompile = new Button("Compile");
        myLanguage = new Button("Language");
        myStep = new Button("Step");
        myClear = new Button("Clear");
        myToolbar.getChildren().addAll(myHelp, myRun, myStep, myCompile, myClear, myLanguage);
        return myToolbar;
    }
    
    
    
    @Override
    public void onCompilePress (EventHandler<MouseEvent> event) {
        // TODO Auto-generated method stub
        myCompile.setOnMouseClicked(event);
        
    }



    @Override
    public void onRunPress (EventHandler<MouseEvent> event) {
        // TODO Auto-generated method stub
        
    }



    @Override
    public void onStepPress (EventHandler<MouseEvent> event) {
        // TODO Auto-generated method stub
        
    }



    @Override
    public void onHelpPress (EventHandler<MouseEvent> event) {
        // TODO Auto-generated method stub
        
    }



    @Override
    public void onLanguagePress (EventHandler<MouseEvent> event) {
        // TODO Auto-generated method stub
        
    }



    @Override
    public void onClearPress (EventHandler<MouseEvent> event) {
        // TODO Auto-generated method stub
        
    }
    
}
