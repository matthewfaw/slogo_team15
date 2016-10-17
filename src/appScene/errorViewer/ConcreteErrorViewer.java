package appScene.errorViewer;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ConcreteErrorViewer implements IErrorViewer {

	private ScrollPane myErrorViewer;
	private List<Object> errorList;
	private List<Button> errorButtonList;
	
	private static final int ErrorRowWidth = 100;
	
	
	public ConcreteErrorViewer(int width, int height){
		errorList = new ArrayList<Object>();
		
		myErrorViewer = new ScrollPane();
		myErrorViewer.setHbarPolicy(ScrollBarPolicy.NEVER);
		myErrorViewer.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		myErrorViewer.setMaxSize(ErrorRowWidth, height/5);
		myErrorViewer.setMinSize(ErrorRowWidth, height/5);
		
		VBox columnBox = initErrorColumn();
		for(int i=0; i < 20; i++){
			HBox currRowBox = createErrorRow("Error: " + Integer.toString(i+1));
			columnBox.getChildren().add(currRowBox);
		}
		
		myErrorViewer.setContent(columnBox);
		
	}
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Node getInstanceAsNode() {
		// TODO Auto-generated method stub
		return myErrorViewer;
	}

	@Override
	public void giveErrorStructure(Object ErrorStructure) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onErrorPress() {
		// TODO Auto-generated method stub
		
	}

	private HBox createErrorRow(String errorMsg){
		HBox currRowBox = new HBox(0);
		currRowBox.setStyle("-fx-background-color: #FFFF00");
		Label label = new Label(errorMsg);
		label.setMinWidth(ErrorRowWidth/2);
		Button goToButton = new Button("Go To");
		goToButton.setMinWidth(ErrorRowWidth/2);
		currRowBox.getChildren().addAll(label, goToButton);
		return currRowBox;
	}
	
	private VBox initErrorColumn(){
		VBox columnBox = new VBox(3);
		columnBox.setStyle("-fx-background-color: #FF6699");	
		return columnBox;
	}
	
	
}
