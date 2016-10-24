package front_end.appScene.variableViewer;

import javafx.beans.property.SimpleStringProperty;

class Variable {
	private final SimpleStringProperty myName;
	private final SimpleStringProperty myValue;
	
	Variable(String aName, Number aValue){
		myName = new SimpleStringProperty(aName);
		myValue = new SimpleStringProperty(aValue.toString());
	}

	SimpleStringProperty getNameProperty(){
		return myName;
	}
	
	SimpleStringProperty getValueProperty(){
		return myValue;
	}
	
	String getName() {
		return myName.get();
	}

	void setName(String aName) {
		myName.set(aName);
	}	
	
	String getValue(){
		return 	myValue.get();
	}
	
	void setValue(Number aValue){
		myValue.set(aValue.toString());
	}
}
