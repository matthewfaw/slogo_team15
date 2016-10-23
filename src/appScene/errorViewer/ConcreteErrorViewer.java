package appScene.errorViewer;

import java.util.ArrayList;
import java.util.List;

import appScene.textEditor.ITextEditor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

class ConcreteErrorViewer implements IErrorViewer {

	private ScrollPane myErrorScroller;
	private ITextEditor myTextEditor;
	private List<Exception> myErrorList;
	private List<Button> myErrorButtonList;
	
	private static final int ErrorRowHeight = 30;
	
	ConcreteErrorViewer(int aWidth, int aHeight, ITextEditor aTextEditor){
		myTextEditor = aTextEditor;
		
		myErrorList = new ArrayList<>();
		myErrorButtonList = new ArrayList<>();
		
		myErrorScroller = new ScrollPane();		
		myErrorScroller.setHbarPolicy(ScrollBarPolicy.NEVER);
		myErrorScroller.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		myErrorScroller.setMinSize(aWidth, aHeight);
		myErrorScroller.setMaxSize(aWidth, aHeight);
		
		VBox columnBox = initErrorColumn();
		for(int i=0; i < 3; i++){
			HBox currRowBox = createErrorRow(aWidth - 10, "Error: " + Integer.toString(i+1) + " | Highlights row 5");
			columnBox.getChildren().add(currRowBox);
		}
		
		setErrorButtonEvents();
		
		myErrorScroller.setContent(columnBox);
		
	}
	
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Node getInstanceAsNode() {
		// TODO Auto-generated method stub
		return myErrorScroller;
	}

	@Override
	public void giveErrorStructure(Exception aError) {
		myErrorList.add(0, aError);
		// TODO: implement error viewing
	}

	@Override
	public void onErrorPress() {
		// TODO Auto-generated method stub
		
	}
	
	private void setErrorButtonEvents(){
		for(Integer i = 0; i < myErrorButtonList.size(); i++){
			myErrorButtonList.get(i).setOnMouseClicked( event -> {  
				myTextEditor.highlightLine(Color.RED, 4); });
		}
	}

	
	
	private HBox createErrorRow(int width, String errorMsg){
		HBox currRowBox = new HBox(0);
		currRowBox.setPrefSize(width , ErrorRowHeight);
		
		Label label = new Label(errorMsg);
		
		Button goToButton = new Button("Go To");
		myErrorButtonList.add(goToButton);
		
		label.setMinWidth(width/2);
		currRowBox.getChildren().addAll(label, goToButton);
		return currRowBox;
	}
	
	private VBox initErrorColumn(){
		VBox columnBox = new VBox(3);
		return columnBox;
	}
	
	
}
