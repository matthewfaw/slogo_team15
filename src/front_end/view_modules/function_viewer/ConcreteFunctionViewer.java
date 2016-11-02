package front_end.view_modules.function_viewer;

import java.util.Collection;
import java.util.Iterator;
import back_end.model.states.IViewableMethodState;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * 
 * @author Kayla Schulz
 *
 */
class ConcreteFunctionViewer implements IFunctionViewer {

    private ScrollPane myScriptViewer;
    private final int SPACING = 10;

    private VBox myFunctionVBox;
    private Collection<String> myMethods;

    ConcreteFunctionViewer (int aWidth, int aHeight) {
        myScriptViewer = new ScrollPane();
        myScriptViewer.setHbarPolicy( ScrollPane.ScrollBarPolicy.NEVER  );
        myScriptViewer.setVbarPolicy( ScrollPane.ScrollBarPolicy.ALWAYS );
        
        myScriptViewer.setPrefSize(aWidth, aHeight);
        myFunctionVBox = new VBox(SPACING);
        myScriptViewer.setContent(myFunctionVBox);
    }
    
    @Override
    public void reset () {
        myFunctionVBox.getChildren().clear();
    }

    @Override
    public Node getInstanceAsNode () {
        return myScriptViewer;
    }
    
    private HBox createHBox(String myString) {
        HBox myHBox = new HBox(SPACING);
        Label myLabel = new Label(myString);
        myHBox.getChildren().addAll(myLabel);
        return myHBox;
    }
    
    private void displayMethods() {
        Iterator<String> myIterator = myMethods.iterator();
        myFunctionVBox.getChildren().clear();
        while(myIterator.hasNext()) {
            String temp = myIterator.next();
            HBox myHBox = createHBox(temp);
            myFunctionVBox.getChildren().add(myHBox);
        }
    }

    @Override
    public void giveFunction (IViewableMethodState aMethod) {
        myMethods = aMethod.getAllMethodNames();
        displayMethods();
    }

}
