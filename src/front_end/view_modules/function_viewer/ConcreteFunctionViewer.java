package front_end.view_modules.function_viewer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.Button;
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
    private LinkedList<String> myFunctions;
    private List<String> fiveShownFunctions;
    private VBox myFunctionVBox;
    private List<String> myCurFunction;

    ConcreteFunctionViewer (int aWidth, int aHeight) {
        myScriptViewer = new ScrollPane();
        myScriptViewer.setHbarPolicy( ScrollPane.ScrollBarPolicy.NEVER  );
        myScriptViewer.setVbarPolicy( ScrollPane.ScrollBarPolicy.ALWAYS );
        
        myFunctions = new LinkedList<String>();
        myScriptViewer.setPrefSize(aWidth, aHeight);
        myFunctionVBox = new VBox(SPACING);
        myScriptViewer.setContent(myFunctionVBox);
        myCurFunction = new ArrayList<String>();
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
        Button myButton = makeButton(myString);
        myHBox.getChildren().addAll(myLabel, myButton);
        return myHBox;
    }
    
    private void getFiveFuncs() {
        int i = 0;
        Iterator<String> myIterator = myFunctions.iterator();
        fiveShownFunctions = new ArrayList<String>();
        myFunctionVBox.getChildren().clear();
        while(myIterator.hasNext() && i < 5) {
            String temp = myIterator.next();
            fiveShownFunctions.add(temp);
            HBox myHBox = createHBox(temp);
            myFunctionVBox.getChildren().add(myHBox);
            i++;
        }
    }
    
    private Button makeButton (String myFunctionString) {
        Button myButton = new Button("Press for Function");
        return myButton;
    }

    @Override
    public void giveFunction (String myFunction) {
        myFunction = myFunction.trim();
        if (!myFunctions.contains(myFunction)) {
            myFunctions.addFirst(myFunction);
            getFiveFuncs();
        }
        System.out.println(myFunctions.size());
    }

}
