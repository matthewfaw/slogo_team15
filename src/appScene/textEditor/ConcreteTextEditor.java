package appScene.textEditor;

import java.util.List;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ConcreteTextEditor implements ITextEditor{

    private ScrollPane myTextEditor;
    
    public ConcreteTextEditor(int aWidth, int aHeight){
    	myTextEditor = new ScrollPane();
        myTextEditor.setHbarPolicy(ScrollBarPolicy.NEVER);
        myTextEditor.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        VBox vBox = new VBox(0);
        List<TextField> myTextFields = new ArrayList<TextField>();
        
        for (int i = 0; i < 29; i++) {
            TextField curTextField = new TextField();
            curTextField.setMinWidth(aWidth-50);
            curTextField.setMaxWidth(aWidth-50);
            HBox hBox = new HBox(20);
            HBox labelBox = new HBox(20);
            Label curLabel = new Label(Integer.toString(i+1));
            labelBox.setMinWidth(20);
            labelBox.getChildren().add(curLabel);
            hBox.getChildren().addAll(labelBox, curTextField);
            myTextFields.add(curTextField);
            vBox.getChildren().add(hBox);
        }
        myTextEditor.setPrefSize(aWidth+5, aHeight+5);
        myTextEditor.setContent(vBox);
    }
   
    @Override
    public Node getInstanceAsNode(){
        return myTextEditor;
    }
    
    @Override
    public void clear () {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void highlightLine (Color color, int line) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<String> getInstructionList () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setInstructionList (List<String> instructions) {
        // TODO Auto-generated method stub
        
    }

}
