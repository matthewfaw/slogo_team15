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

class ConcreteTextEditor implements ITextEditor{

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
    private static final int SPACING = 20;
    
    /******* API Defined Methods ********/
       
    @Override
    public Node getInstanceAsNode(){
        return myTextEditor;
    }
    
    @Override
    public void reset() {
         configureStartParameters(myWidth, myHeight);
         initTextColumn();
         initTextScroller(myTextColumn);
         
         myTextFields.get(1).setText("Press Here to Make Me RED");
         myTextFields.get(1).setOnMouseClicked( e-> {
        	 highlightLine(Color.RED, 1);
         });
         
    }

    @Override
    public void highlightLine (Color color, int line) {
    	if(line >= myLastIndex) return;
    	
    	Background highlight = new Background( new BackgroundFill(
    			color,
    			CornerRadii.EMPTY,
    			Insets.EMPTY
    			));
    	
    	myRows.get(line).setBackground(highlight);
    }

    @Override
    public List<String> getInstructionList () {
    	List<String> outputList = new ArrayList<>();
    	
    	for (int i = 0; i < myTextFields.size(); i++) {
			outputList.add(i, myTextFields.get(i).getText());
		}
    	
        return outputList;
    }

    @Override
    public void setInstructionList (List<String> instructions) {
        // TODO Auto-generated method stub
        
    }

    /******** Package Visible Methods *********/
    
    ConcreteTextEditor(int aWidth, int aHeight){
    	configureStartParameters(aWidth, aHeight);
        initTextColumn();
        initTextScroller(myTextColumn);
    }
    
    void clearTextFields(){
    	for(TextField tf: myTextFields){
    		tf.clear();
    	}
    }
    
    /******** Private Helper Methods *********/
    
    private void configureStartParameters(int aWidth, int aHeight){
    	myWidth = aWidth;
    	myHeight = aHeight;
    	myLastIndex = 0;
    	myRowsAdded = false;
    	
        myTextFields = new ArrayList<TextField>();
        myRows = new ArrayList<HBox>();
    }
    
    private void initTextColumn(){
   	 myTextColumn = new VBox(0);
   	 initTextToolbar();
    	 while(myLastIndex < NUM_START_ROWS) newLine();   	
    }

    private void initTextToolbar(){
    	myTextColumn.getChildren().add(new TextEditorToolbar(this).getBar());
    }
    
    private void newLine(){
    	 HBox rowBox = new HBox(SPACING);
    	
    	TextField curTextField = new TextField();
        curTextField.setMinWidth(myWidth-2*SPACING);
        curTextField.setMaxWidth(myWidth-2*SPACING);
        myTextFields.add(myLastIndex, curTextField);
        
    	curTextField.setOnMouseClicked(e -> {
    		if(curTextField.equals(myTextFields.get(myLastIndex - 1))){
    			myRowsAdded = true;
    			for(int i = 0; i < NEW_ROW_BURST; i++) newLine();
    		}    		
    	});
        
        HBox labelBox = new HBox(SPACING);
        Label curLabel = new Label(Integer.toString(myLastIndex + 1));
        labelBox.setMinWidth(SPACING);
        labelBox.getChildren().add(curLabel);
        
        rowBox.getChildren().addAll(labelBox, curTextField);
        myRows.add(myLastIndex, rowBox);       
        myTextColumn.getChildren().add(rowBox);
        
        myLastIndex++;
    }
    
    private void initTextScroller( Node aContent ){
    	
    	if(myTextEditor == null)
    		myTextEditor = new ScrollPane();
    	else{
    		myTextEditor.setContent(aContent);
    	}
    	
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
