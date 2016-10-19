package appScene.textEditor;

import java.util.List;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ConcreteTextEditor implements ITextEditor{

    private ScrollPane myTextEditor;
    private VBox myTextColumn;
    private List<TextField> myTextFields;
    private List<HBox> myRows;
    
    private int myWidth;
    private int myHeight;
    
    private int myLastIndex;
    private boolean myRowsAdded;
    
    private static final int NUM_START_ROWS = 5;
    private static final int NEW_ROW_BURST = 5;
    
    public ConcreteTextEditor(int aWidth, int aHeight){
    	myWidth = aWidth;
    	myHeight = aHeight;
    	myLastIndex = 0;
    	
        myTextColumn = new VBox(0);
        myTextFields = new ArrayList<TextField>();
        myRows = new ArrayList<HBox>();
        
        initTextColumn();
        initTextScroller(myTextColumn);
    }
   
    @Override
    public Node getInstanceAsNode(){
        return myTextEditor;
    }
    
    @Override
    public void clear () {
         myTextFields = new ArrayList<TextField>();
         
         initTextColumn();
         initTextScroller(myTextColumn);
    }

    @Override
    public void highlightLine (Color color, int line) {
    	if(line >= myLastIndex) return;
    	
    	myRows.get(line).setBackground(new Background( new BackgroundFill(
    			color,
    			CornerRadii.EMPTY,
    			Insets.EMPTY
    			)));
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

    private void initTextColumn(){
   	 myTextColumn = new VBox(0);
    	 while(myLastIndex < NUM_START_ROWS) newLine();   	
    }

    private void newLine(){
    	 HBox rowBox = new HBox(20);
    	
    	TextField curTextField = new TextField();
        curTextField.setMinWidth(myWidth-50);
        curTextField.setMaxWidth(myWidth-50);
        myTextFields.add(myLastIndex, curTextField);
        
    	curTextField.setOnMouseClicked(e -> {
    		if(curTextField.equals(myTextFields.get(myLastIndex - 1))){
    			myRowsAdded = true;
    			for(int i = 0; i < NEW_ROW_BURST; i++) newLine();
    		}
    	});
        
        
        HBox labelBox = new HBox(20);
        Label curLabel = new Label(Integer.toString(myLastIndex + 1));
        labelBox.setMinWidth(20);
        labelBox.getChildren().add(curLabel);
        
        rowBox.getChildren().addAll(labelBox, curTextField);
        myRows.add(myLastIndex, rowBox);
                
        myTextColumn.getChildren().add(rowBox);
        
        myLastIndex++;
    }
    
    private void initTextScroller( Node aContent ){
    	myTextEditor = new ScrollPane();
        myTextEditor.setHbarPolicy(ScrollBarPolicy.NEVER);
        myTextEditor.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        myTextEditor.setPrefSize(myWidth + 5, myHeight);
        
        myTextEditor.vvalueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if(myRowsAdded){
					myRowsAdded = false;
					myTextEditor.setVvalue(myTextEditor.getVmax());
				}
				
			}});
        myTextEditor.setContent(aContent);
    }
}
