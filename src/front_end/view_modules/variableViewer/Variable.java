package front_end.view_modules.variableViewer;

import javafx.beans.property.SimpleStringProperty;


/**
 * Helper class for Variable Viewer TableView
 * 
 * @author George Bernard
 */
class Variable {
    private final SimpleStringProperty myName;
    private final SimpleStringProperty myValue;

    /**
     * Constructs a variable from a name and value
     * @param aName
     * @param aValue
     */
    Variable (String aName, Number aValue) {
        myName = new SimpleStringProperty(aName);
        myValue = new SimpleStringProperty(aValue.toString());
    }

    /**
     * To use the tableview, the name is stored as a string property
     * @see SimpleStringProperty
     * @return name as a StringProperty
     */
    SimpleStringProperty getNameProperty () {
        return myName;
    }

    /**
     * To use the tableview, the value is stored as a string property
     * @see SimpleStringProperty
     * @return value as a StringProperty
     */
    SimpleStringProperty getValueProperty () {
        return myValue;
    }

    /**
     * Gets the string name of the variable
     * @return
     */
    String getName () {
        return myName.get();
    }

    
    /**
     * Sets the name of the variable
     * @param aName
     */
    void setName (String aName) {
        myName.set(aName);
    }

    /**
     * gets value of variable as string
     * @return value of variable as string
     */
    String getValue () {
        return myValue.get();
    }

    /**
     * Sets value of variable fron number input
     * @param aValue
     */
    void setValue (Number aValue) {
        myValue.set(aValue.toString());
    }
}
