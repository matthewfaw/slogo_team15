package appScene.textEditor;

import java.util.List;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ConcreteTextEditor implements ITextEditor{

    private ScrollPane myTextEditor;
    
    public Node initTextEditor(int width, int height) {
        myTextEditor = new ScrollPane();
        myTextEditor.setHbarPolicy(ScrollBarPolicy.NEVER);
        myTextEditor.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        VBox vBox = new VBox(0);
        List<TextField> myTextFields = new ArrayList<TextField>();
        
        for (int i = 0; i < 20; i++) {
            TextField curTextField = new TextField();
            myTextFields.add(curTextField);
            vBox.getChildren().add(curTextField);
        }
        myTextEditor.setContent(vBox);
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
